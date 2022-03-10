package projectA.projectA.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import projectA.projectA.entity.base.AutoID;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "user_profile")
public class User extends AutoID {

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "sex", nullable = false)
    private String sex;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String passWord;

    @Column(name = "create_date", nullable = false)
    private Date date;

    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @Column(name = "picture", nullable = true)
    private String picture = "2022-03-10_01-30-34.png";

    @Column(name = "new_email",nullable = true)
    private String newEmail;

    @Column(name = "verify_email", nullable = false)
    private Integer verifyEmail;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Choice_apply_work> choice;

    public enum Role {ADMIN, USER}
}
