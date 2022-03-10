package projectA.projectA.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "company_work")
public class CompanyWork {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "company_name", nullable = false) //ชื่อบริษัท
  private String companyName;

  @Column(name = "province", nullable = false) // จังหวัด
  private String province;

  @Column(name = "district", nullable = false) // อำเภอ
  private String district;

  @Column(name = "job_title", nullable = false) // ตำแหน่งงาน
  private String jobTitle;

  @Column(name = "salary", nullable = false) // เงินเดือน
  private String salary;

  @Column(name = "welfare_benefits", nullable = true) // สวัสดิการ
  private String welfareBenefits;

  @Column(name = "detail_work", nullable = true) // รายละเอียดงาน
  private String detailWork;

  @Column(name = "feature", nullable = true) // คุณสมบัติ
  @Lob
  private String feature;

  @Column(name = "contact", nullable = false) // ติดต่อที่
  private String contact;

  @Column(name = "create_date", nullable = false)
  private Date date = new Date();

  @Column(name = "update_date", nullable = false)
  private Date updateDate = new Date();

  @Column(name = "picture")
  private String picture;

  @ManyToOne
  @JoinColumn(name = "company_id",nullable = false) //company id
  private Company company;

  @OneToMany(mappedBy = "CompanyWork",orphanRemoval = true)
  private List<Choice_apply_work> choice_apply_works;

}
