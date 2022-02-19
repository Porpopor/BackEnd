package projectA.projectA.API;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectA.projectA.business.CompanyWorkBusiness;
import projectA.projectA.exception.BaseException;
import projectA.projectA.model.companyWorkModel.CompanyWorkDelete;
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
  public ResponseEntity<Object> createComp(@RequestBody CompanyWorkReq request) throws BaseException {
    Object response = companyWorkBusiness.createCompanyWork(request);
//    APIResponse apiResponse = new APIResponse();
//    apiResponse.setData(response);
    return ResponseEntity.ok(response);

  }

  @PostMapping("/edit")
  public ResponseEntity<Object> editComp(@RequestBody CompanyWorkReq request) throws BaseException {
    Object response = companyWorkBusiness.editCompanyWork(request);
    return ResponseEntity.ok(response);
  }

  @PostMapping ("/listByUser")
  public ResponseEntity<Object>listCompByUser() throws BaseException {
    Object response = companyWorkBusiness.listByUser();
    return ResponseEntity.ok(response);
  }

  @PostMapping ("/listAll")
  public ResponseEntity<Object>listAll() throws BaseException {
    Object response = companyWorkBusiness.listAll();
    return ResponseEntity.ok(response);
  }

  @PostMapping ("/listAllByProvince")
  public ResponseEntity<Object>listAllByProvince(@RequestBody CompanyWorkReq request) throws BaseException {
    Object response = companyWorkBusiness.listAllByProvince(request);
    return ResponseEntity.ok(response);
  }

  @PostMapping ("/delete")
  public ResponseEntity<Object>delete(@RequestBody CompanyWorkDelete request) throws BaseException {
    Object response = companyWorkBusiness.delete(request);
    return ResponseEntity.ok(response);
  }
}

