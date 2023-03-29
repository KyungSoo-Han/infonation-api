package kr.infonation.domain.users;

import com.sun.istack.NotNull;
import kr.infonation.domain.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    private String phoneNo;
    private String birthDate;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Users(String email, String name, String password, String phoneNo, String birthDate, Role role) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phoneNo = phoneNo;
        this.birthDate = birthDate;
        this.role = role;
    }

}
