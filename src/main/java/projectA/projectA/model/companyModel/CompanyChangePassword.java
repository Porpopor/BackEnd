package projectA.projectA.model.companyModel;

import lombok.Data;

@Data
public class CompanyChangePassword {
    private String oldPassWord;
    private String newPassWord;
}
