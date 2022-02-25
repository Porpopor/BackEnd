package projectA.projectA.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projectA.projectA.business.UserBusiness;
import projectA.projectA.entity.User;
import projectA.projectA.exception.BaseException;
import projectA.projectA.model.userModel.ForgetPassword;
import projectA.projectA.model.userModel.LoginReq;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/sentEmail")
public class SentEmailController {

    private final UserBusiness userBusiness;

    public SentEmailController(UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }

    @PostMapping("/forget-password")
    public ResponseEntity<Object> forgetPassword(@RequestBody ForgetPassword request) throws BaseException, MessagingException {
        Object response = userBusiness.forgetPassword(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify-email")
    public ResponseEntity<Object> verifyEmail(@RequestBody LoginReq request) throws BaseException, MessagingException {
        Object response = userBusiness.verifyEmail(request);
        return ResponseEntity.ok(response);
    }
}
