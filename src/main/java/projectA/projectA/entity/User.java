package projectA.projectA.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import projectA.projectA.entity.base.AutoID;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_name")
public class User{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false,unique = true)
  private int id;

  @Column(name = "email",nullable = false)
  private String email;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "password",length = 50)
  private String passWord;
}
