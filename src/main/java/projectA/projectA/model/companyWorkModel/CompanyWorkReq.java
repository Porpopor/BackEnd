package projectA.projectA.model.companyWorkModel;

import lombok.Data;

import java.util.Date;

@Data
public class CompanyWorkReq {

  private Integer id;
  private String detail;
  private String province;
  private Date date;
  private Date updateDate;
}
