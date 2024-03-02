package h9w.me.transactional.member.controller;

import h9w.me.transactional.member.dto.MemberDTO;
import h9w.me.transactional.member.dto.ResponseDTO;
import h9w.me.transactional.member.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("")
    public List<MemberDTO> findMembers(@RequestParam Map<String, String> params) {
        MemberDTO searchInfo = new MemberDTO();

        String memberId = Optional.ofNullable(params.get("memberId")).orElse("");
        String memberName = Optional.ofNullable(params.get("memberName")).orElse("");

        List<MemberDTO> members = memberService.findMemberes(searchInfo);

        return members;
    }

    @PostMapping("")
    public void registMember(MemberDTO registMemberInfo) {
        memberService.registMember(registMemberInfo);
    }

    @PostMapping("/transaction-test")
    public ResponseDTO txTest(@RequestParam("code") String code) {
        ResponseDTO response = null;

        String resultCode = memberService.txText(code);


        if("ok".equals(resultCode)) {
            response = ResponseDTO.success(resultCode, null);
        } else if ("saveError".equals(resultCode)) {
            response = ResponseDTO.fail(resultCode, null);
        } else if ("fileNotFound".equals(resultCode)) {
            response = ResponseDTO.fail(resultCode, null);
        } else {
            response = ResponseDTO.fail("server error", null);
        }
        return response;
    }
}
