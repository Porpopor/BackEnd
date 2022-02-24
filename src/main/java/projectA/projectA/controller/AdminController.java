package projectA.projectA.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projectA.projectA.business.CompanyWorkBusiness;
import projectA.projectA.business.UserBusiness;
import projectA.projectA.exception.BaseException;
import projectA.projectA.model.companyWorkModel.CompanyWorkDelete;
import projectA.projectA.model.userModel.LoginReq;

@RestController
@RequestMapping("/admin")
public class AdminController {

  public final UserBusiness userBusiness;
  public final CompanyWorkBusiness companyWorkBusiness;

  public AdminController(UserBusiness userBusiness, CompanyWorkBusiness companyWorkBusiness) {
    this.userBusiness = userBusiness;
    this.companyWorkBusiness = companyWorkBusiness;
  }

  @PostMapping("/login")
  public ResponseEntity<Object>login(@RequestBody LoginReq request) throws BaseException {
    Object response = userBusiness.LoginAdmin(request);
    return ResponseEntity.ok(response);
  }

  @PostMapping ("/delete-companyWork")
  public ResponseEntity<Object>delete(@RequestBody CompanyWorkDelete request) throws BaseException {
    Object response = companyWorkBusiness.deleteByAdmin(request);
    return ResponseEntity.ok(response);
  }
}
