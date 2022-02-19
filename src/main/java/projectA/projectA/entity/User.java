package projectA.projectA.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import projectA.projectA.entity.base.AutoID;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "user_name")
public class User extends AutoID{

  @Column(name = "email",nullable = false)
  private String email;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @JsonIgnore
  @Column(name = "password")
  private String passWord;

  @JsonFormat(pattern = "dd-MM-yyyy")
  @Column(name = "create_date", nullable = false)
  private Date date;

  @Column(name = "phone")
  private String phone;

  @Column(name = "name_Company")
  private String nameCompany;

  @Column(name = "role")
  @Enumerated(EnumType.STRING)
  private Role role = Role.USER;

//  @OneToOne(mappedBy = "user",orphanRemoval = true)
//  private UserProfile profileUser;

  @OneToMany(orphanRemoval = true, mappedBy = "user")
  private List<CompanyWork> CompanyWork;

  public enum Role {ADMIN,USER}
}
