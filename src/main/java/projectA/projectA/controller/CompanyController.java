package projectA.projectA.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectA.projectA.business.CompanyBusiness;
import projectA.projectA.exception.BaseException;
import projectA.projectA.exception.CompanyException;
import projectA.projectA.model.companyModel.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyBusiness companyBusiness;

    public CompanyController(CompanyBusiness companyBusiness) {
        this.companyBusiness = companyBusiness;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody CompanyRegisterReq request) throws BaseException {
        Object response = companyBusiness.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody CompanyLogin request) throws BaseException {
        Object response = companyBusiness.login(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/editCompany")
    public ResponseEntity<Object> editCompany(@RequestBody CompanyEditReq request) throws BaseException {
        Object response = companyBusiness.editCompanyProfile(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/change-email")
    public ResponseEntity<Object> changeEmail(@RequestBody CompanyEmailReq request) throws BaseException, MessagingException {
        Object response = companyBusiness.changeEmail(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sentVerify-email")
    public ResponseEntity<Object> sentVerifyEmail() throws BaseException, MessagingException {
        Object response = companyBusiness.sentVerifyEmail();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/verify-email")
    public ResponseEntity<Object> verifyEmail() throws BaseException {
        Object response = companyBusiness.VerifyEmail();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/change-password")
    public ResponseEntity<Object> changePassword(@RequestBody CompanyChangePassword request) throws BaseException {
        Object response = companyBusiness.changePassword(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/companyForget-password")

    public ResponseEntity<Object> companyForgetPassword(@RequestBody CompanyForgetPassword request) throws MessagingException, CompanyException {
        Object response = companyBusiness.companyForgetPassword(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/reset-password")
    public ResponseEntity<Object> resetPassword(@RequestBody CompanyResetPassword request) throws BaseException {
        Object response = companyBusiness.companyResetPassword(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile")
    public ResponseEntity<Object>companyProfile() throws BaseException {
        Object response = companyBusiness.findById();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/check-role")
    public ResponseEntity<Object>checkRoleUser() throws BaseException {
        Object response = companyBusiness.checkRoleCompany();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<Object>refreshToken() throws BaseException {
        Object response = companyBusiness.refreshToken();
        return ResponseEntity.ok(response);
    }
}
