package projectA.projectA.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import projectA.projectA.entity.base.AutoID;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "company_profile")
public class Company extends AutoID {

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String passWord;

    @Column(name = "create_date", nullable = false)
    private Date date;

    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "picture", nullable = true)
    private String picture;

    @Column(name = "type", nullable = true)
    private String type;

    @Column(name = "new_email",nullable = true)
    private String newEmail;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role = Role.COMPANY;

    @Column(name = "verify_email", nullable = false)
    private Integer verifyEmail;

    @OneToMany(orphanRemoval = true, mappedBy = "company")
    private List<CompanyWork> companyWork;

    public enum Role {ADMIN, COMPANY}

}
