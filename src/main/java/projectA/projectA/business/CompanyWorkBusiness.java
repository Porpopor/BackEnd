package projectA.projectA.business;

import org.springframework.stereotype.Service;
import projectA.projectA.entity.User;
import projectA.projectA.exception.BaseException;
import projectA.projectA.model.Response;
import projectA.projectA.model.companyWorkModel.CompanyWorkReq;
import projectA.projectA.service.CompanyWorkService;
import projectA.projectA.service.TokenService;

@Service
public class CompanyWorkBusiness {

  private final TokenService tokenService;
  private final CompanyWorkService companyWorkService;

  public CompanyWorkBusiness(TokenService tokenService, CompanyWorkService companyWorkService) {
    this.tokenService = tokenService;
    this.companyWorkService = companyWorkService;
  }

  public Object createCompanyWork(CompanyWorkReq request) throws BaseException {
    User user = tokenService.getUserByIdToken();

    companyWorkService.createCompanyWork(user,request.getDetail());
    return new Response().success("สร้างสำเร็จ");
  }

}
