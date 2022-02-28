//package projectA.projectA.controller;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import projectA.projectA.business.UserBusiness;
//import projectA.projectA.exception.BaseException;
//import projectA.projectA.model.userModel.UserForgetPassword;
//
//import javax.mail.MessagingException;
//
//@RestController
//@RequestMapping("/sentEmail")
//public class SentEmailController {
//
//    private final UserBusiness userBusiness;
//
//    public SentEmailController(UserBusiness userBusiness) {
//        this.userBusiness = userBusiness;
//    }
//
//    @PostMapping("/userForget-password")
//    public ResponseEntity<Object> userForgetPassword(@RequestBody UserForgetPassword request) throws BaseException, MessagingException {
//        Object response = userBusiness.userForgetPassword(request);
//        return ResponseEntity.ok(response);
//    }
//}
