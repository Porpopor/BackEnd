package projectA.projectA.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "company_work")
public class CompanyWork {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "detail")
  private String detail;

  @ManyToOne
  @JoinColumn(name = "user_id",nullable = false)
  private User user;
}
