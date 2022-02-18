package projectA.projectA.API;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectA.projectA.business.CompanyWorkBusiness;
import projectA.projectA.exception.BaseException;
import projectA.projectA.model.companyWorkModel.CompanyWorkReq;
import projectA.projectA.model.userModel.UserEditReq;

@RestController
@RequestMapping("/company-work")
public class CompanyWorkAPI {

  private final CompanyWorkBusiness companyWorkBusiness;

  public CompanyWorkAPI(CompanyWorkBusiness companyWorkBusiness) {
    this.companyWorkBusiness = companyWorkBusiness;
  }
  @PostMapping("/create")
  public ResponseEntity<Object> editUser(@RequestBody CompanyWorkReq request) throws BaseException {
    Object response = companyWorkBusiness.createCompanyWork(request);
//    APIResponse apiResponse = new APIResponse();
//    apiResponse.setData(response);
    return ResponseEntity.ok(response);

  }
}

