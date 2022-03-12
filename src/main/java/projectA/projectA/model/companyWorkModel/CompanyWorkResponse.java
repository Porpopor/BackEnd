package projectA.projectA.model.companyWorkModel;

import lombok.Data;

import java.util.Date;

@Data
public class CompanyWorkResponse {

  private Integer id;
  private String companyName;
  private String province;
  private String district;
  private String jobTitle;
  private String salary;
  private String welfareBenefits;
  private String detailWork;
  private String feature;
  private String contact;
  private String picture;
}
