package h9w.me.transactional.member.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TBL_MEMBER")
@SequenceGenerator(
        name = "SEQ_TBL_MEMBER_GENERATOR",
        sequenceName = "SEQ_TBL_MEMBER",
        allocationSize = 1
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Member {

    @Id
    @Column(name = "MEMBER_NO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBL_MEMBER_GENERATOR")
    private long memberNo;

    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "MEMBER_NAME")
    private String memberName;
}
