package h9w.me.transactional.member.repository;

import h9w.me.transactional.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    List<Member> findByMemberId(String memberId);

    List<Member> findByMemberName(String memberName);

    List<Member> findByMemberIdAndMemberName(String memberId, String memberName);
}
