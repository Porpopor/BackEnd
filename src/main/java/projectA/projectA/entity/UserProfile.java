package projectA.projectA.entity;

import lombok.Data;
import projectA.projectA.entity.base.AutoID;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_profile")
public class UserProfile extends AutoID {

  @Column(length = 15, unique = true)
  private String phone;

  @Column(name = "name_Company")
  private String nameCompany;

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;
}
