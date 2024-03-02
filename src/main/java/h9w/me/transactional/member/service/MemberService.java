package h9w.me.transactional.member.service;

import h9w.me.transactional.member.dto.MemberDTO;
import h9w.me.transactional.member.entity.Member;
import h9w.me.transactional.member.repository.MemberRepository;
import org.hibernate.exception.DataException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {

    private final MemberRepository memberRepo;
    private final ModelMapper mapper;

    public MemberService(MemberRepository memberRepo, ModelMapper mapper) {
        this.memberRepo = memberRepo;
        this.mapper = mapper;
    }

    public List<MemberDTO> findMemberes(MemberDTO searchInfo) {
        String memberId = Optional.ofNullable(searchInfo.getMemberId()).orElse("");
        String memberName = Optional.ofNullable(searchInfo.getMemberName()).orElse("");

        List<Member> members = null;
        //검색조건 없음
        if(memberId.isEmpty() && memberName.isEmpty()) {
            members = memberRepo.findAll();
        } else {
            //id, name 검색
            if(!memberId.isEmpty() && !memberName.isEmpty()) {
                members = memberRepo.findByMemberIdAndMemberName(memberId, memberName);

            //name으로 검색
            } else if(!memberId.isEmpty() && memberName.isEmpty()){
                members = memberRepo.findByMemberName(memberName);

            //id로 검색
            } else if(memberId.isEmpty() && !memberName.isEmpty()){
                members = memberRepo.findByMemberId(memberId);
            }
        }
        return Optional.ofNullable(members).orElseGet(ArrayList::new).stream()
                .map(v -> mapper.map(v, MemberDTO.class)).collect(Collectors.toList());
    }

    public void registMember(MemberDTO registInfo) {
        memberRepo.save(mapper.map(registInfo, Member.class));
    }

    public String txText(String code) {
        String result = "";
        try {
            fileUploadFunc(code);

            saveMember(code);
            if(Optional.ofNullable(code).orElse("").contains("saveError")) {
                saveMember(code);
            }
            result = "ok";

        } catch (FileNotFoundException e) {
            result = "fileNotFound";

        } catch (Exception e) {
            result = "saveError";

        }

        return result;
    }

    private void saveMember(String code) throws Exception{
        Member member = Member.builder().memberId("testId").memberName("testName").build();

        memberRepo.save(member);
        memberRepo.flush();
    }

    private void fileUploadFunc(String code) throws FileNotFoundException {
        Optional.ofNullable(code).filter(v-> !v.contains("fileError")).orElseThrow(() -> new FileNotFoundException());
    }
}
