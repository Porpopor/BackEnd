package projectA.projectA.model.companyModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class CompanyRegisterReq {
    private String email;

    private String companyName;

    private String passWord;

    private Date date;

    private String phone;

    private String type;

    private Integer verifyEmail;
}
