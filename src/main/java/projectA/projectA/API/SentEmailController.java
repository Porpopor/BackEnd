package projectA.projectA.API;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projectA.projectA.business.UserBusiness;
import projectA.projectA.exception.BaseException;
import projectA.projectA.exception.UserException;
import projectA.projectA.model.userModel.ForgetPassword;

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
}
