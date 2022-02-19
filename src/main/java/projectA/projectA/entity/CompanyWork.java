package projectA.projectA.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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

  @Column(name = "province", nullable = false)
  private String province;

  @JsonFormat(pattern = "dd-MM-yyyy")
  @Column(name = "create_date", nullable = false)
  private Date date = new Date();

  @JsonFormat(pattern = "dd-MM-yyyy")
  @Column(name = "update_date", nullable = false)
  private Date updateDate = new Date();

  @ManyToOne
  @JoinColumn(name = "user_id",nullable = false)
  private User user;
}
