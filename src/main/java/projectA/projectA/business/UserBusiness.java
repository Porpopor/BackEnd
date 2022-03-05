package projectA.projectA.business;

import org.apache.catalina.security.SecurityUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import projectA.projectA.entity.User;
import projectA.projectA.exception.BaseException;
import projectA.projectA.exception.CompanyException;
import projectA.projectA.exception.UserException;
import projectA.projectA.mapper.UserMapper;
import projectA.projectA.model.Response;
import projectA.projectA.model.UploadFileReq;
import projectA.projectA.model.userModel.*;
import projectA.projectA.service.SentEmailService;
import projectA.projectA.service.TokenService;
import projectA.projectA.service.UserService;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

@Service
public class UserBusiness {

    UploadFileReq url = new UploadFileReq();

    private final UserService userService;
    private final UserMapper userMapper;
    private final TokenService tokenService;
    private final SentEmailService sentEmailService;

    public UserBusiness(UserService userService, UserMapper userMapper, TokenService tokenService, SentEmailService sentEmailService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.tokenService = tokenService;
        this.sentEmailService = sentEmailService;
    }


    public Object LoginUser(UserLoginReq request) throws BaseException {

        Optional<User> byEmail = userService.findByEmail(request.getEmail());
        if (byEmail.isEmpty()) {
            throw UserException.loginFailEmailNotFound();
        }
        User user = byEmail.get();
//    UserEditResponse userEditResponse = userMapper.UserEditToRegisterResponse(user);

        if (!userService.matchPassword(request.getPassWord(), user.getPassWord())) {
            throw UserException.loginFailPasswordIncorrect();
        }
        String token = tokenService.tokenize(user);
        String refreshToken = tokenService.tokenizeRefresh(user);
//    System.out.printf(tokenize);
        return new Response().okLogin("Login success", "token", token,"refreshToken",refreshToken,"role",user.getRole());
    }

    public Object LoginAdmin(UserLoginReq request) throws BaseException {

        Optional<User> byEmail = userService.findByEmail(request.getEmail());
        User user = byEmail.get();


        if (!user.getRole().equals(User.Role.ADMIN)) {
            throw UserException.loginFailEmailNotFound();
        }

        String tokenize = tokenService.tokenize(user);

        return new Response().ok("LoginAdmin success", "token", tokenize);
    }

    public Object register(UserRegisterReq request) throws BaseException {

        if (request.getEmail().isBlank()) {
            throw UserException.EmailNull();
        }
        if (request.getFirstName().isBlank()) {
            throw UserException.FirstNameNull();
        }
        if (request.getLastName().isEmpty()) {
            throw UserException.LastNameNull();
        }
        if (request.getPassWord().isEmpty()) {
            throw UserException.createPasswordNull();
        }
        if (request.getPhone().isEmpty()) {
            throw UserException.phoneNull();
        }
        if (request.getSex().isEmpty()) {
            throw UserException.sexNull();
        }
        if (userService.existsByEmail(request.getEmail())) {
            throw UserException.EmailDuplicated();
        }

        userService.createUser(request.getEmail(), request.getFirstName(),request.getLastName(), request.getSex(), request.getPassWord(), request.getPhone());
        return new Response().success("Register Success");
    }

    public Object editUserProfile(UserEditReq request) throws BaseException {
        User user = tokenService.getUserByIdToken();
        if (request.getFirstName().isBlank()) {
            throw UserException.FirstNameNull();
        }
        if (request.getLastName().isBlank()) {
            throw UserException.LastNameNull();
        }
        if (request.getPhone().isEmpty()) {
            throw UserException.phoneNull();
        }
        if (request.getSex().isEmpty()) {
            throw UserException.sexNull();
        }

        userService.upDateProfile(user, request.getFirstName(), request.getLastName(), request.getSex(), request.getPhone());
        return new Response().success("แก้ไขสำเร็จ");
    }

    public Object userForgetPassword(UserForgetPassword request) throws UserException, MessagingException {

        Optional<User> byEmail = userService.findByEmail(request.getEmail());
        if (byEmail.isEmpty()) {
            throw UserException.notFoundEmailForgetPassword();
        }
        User user = byEmail.get();
        sentEmailService.userForgetPassword(user, request.getEmail());
        return new Response().success("Sent Email Success");
    }

    public Object sentVerifyEmail() throws BaseException, MessagingException {
        User user = tokenService.getUserByIdToken();
        sentEmailService.userVerifyEmail(user, user.getEmail());
        return new Response().success("Sent VerifyEmail Success");
    }

    public Object changeEmail(UserEmailReq request) throws BaseException, MessagingException {

        User user = tokenService.getUserByIdToken();
        if (request.getEmail().isBlank()) {
            throw UserException.EmailNull();
        }
        if (user.getEmail().equals(request.getEmail())) {
            throw UserException.emailVerified();
        }
        userService.changeEmail(user, request.getEmail());
        sentEmailService.userVerifyEmail(user, request.getEmail());
        return new Response().success("Change Email Success");
    }

    public Object ChangePassWord(UserChangePassword request) throws BaseException {
        User user = tokenService.getUserByIdToken();

        if (!userService.matchPassword(request.getOldPassWord(), user.getPassWord())) {
            throw UserException.passwordIncorrect();
        }
        if (userService.matchPassword(request.getNewPassWord(), user.getPassWord())) {
            throw UserException.passwordOldIncorrect();
        }
        if (request.getNewPassWord().isEmpty() || request.getNewPassWord().equals("") || request.getNewPassWord().contains(" ")) {
            throw UserException.createPasswordNull();
        }
        userService.ChangePassWord(user, request.getNewPassWord());

        return new Response().success("ChangePassword Success");
    }

    public Object resetPassWord(UserResetPassWord request) throws BaseException {

        User user = tokenService.getUserByIdToken();
        if (request.getNewPassWord().isBlank()) {
            throw UserException.createPasswordNull();
        }
        if (request.getConfirmPassWord().isBlank()) {
            throw CompanyException.passwordNull();
        }
        if (!request.getNewPassWord().equals(request.getConfirmPassWord())) {
            throw UserException.passwordOldIncorrect();
        }
        userService.ChangePassWord(user, request.getNewPassWord());
        return new Response().success("ResetPassword Success");

    }

    public Object verifyEmail() throws BaseException {
        User user = tokenService.getUserByIdToken();
        userService.verifyEmail(user,user.getNewEmail());
        return new Response().success("VerifyEmail Success");
    }

    public List<UserProfileResponse> list() {
        List<User> all = userService.findAll();
        List<UserProfileResponse> userProfileResponse = userMapper.UserProfileToUserProfileResponse(all);
        return userProfileResponse;
    }

    public Object findById() throws BaseException {

        User userByIdToken = tokenService.getUserByIdToken();

        Optional<User> byId = userService.findById(userByIdToken.getId());
        User user = byId.get();
        user.setPicture(url.getHost() + url.getDirUserProfile() + user.getPicture());
        UserProfileResponse userProfileResponse = userMapper.UserProfileToResponse(user);

        return new Response().ok("Profile", "data", userProfileResponse);
    }

    public Object refreshToken() throws BaseException{
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String Id = (String) authentication.getPrincipal();

        int userId = Integer.parseInt(Id);

        Optional<User> opt = userService.findById(userId);
        if (opt.isEmpty()) {
            throw UserException.notFoundId();
        }
        User user = opt.get();

        String token = tokenService.tokenize(user);
        String refreshToken = tokenService.tokenizeRefresh(user);

        return new Response().okLogin("Refresh Success", "token", token,"refreshToken",refreshToken,"role",user.getRole());
    }

    public Object checkRoleUser() throws BaseException {

        User userByIdToken = tokenService.getUserByIdToken();

        Optional<User> byId = userService.findById(userByIdToken.getId());
        User user = byId.get();

        return new Response().ok("Profile", "role", user.getRole());
    }

}
