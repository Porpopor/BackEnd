package projectA.projectA.business;

import org.springframework.stereotype.Service;
import projectA.projectA.entity.Company;
import projectA.projectA.exception.BaseException;
import projectA.projectA.exception.CompanyException;
import projectA.projectA.model.Response;
import projectA.projectA.model.companyModel.*;
import projectA.projectA.service.CompanyService;
import projectA.projectA.service.SentEmailService;
import projectA.projectA.service.TokenService;

import javax.mail.MessagingException;
import java.util.Optional;

@Service
public class CompanyBusiness {

    private final CompanyService companyService;
    private final TokenService tokenService;
    private final SentEmailService sentEmailService;

    public CompanyBusiness(CompanyService companyService, TokenService tokenService, SentEmailService sentEmailService) {
        this.companyService = companyService;
        this.tokenService = tokenService;
        this.sentEmailService = sentEmailService;
    }

    public Object login(CompanyLogin request) throws BaseException {
        Optional<Company> byEmail = companyService.findByEmail(request.getEmail());
        if (byEmail.isEmpty()) {
            throw CompanyException.loginFail();
        }
        Company company = byEmail.get();
        if (!companyService.matchPassword(request.getPassWord(), company.getPassWord())) {
            throw CompanyException.loginFail();
        }

        String token = tokenService.tokenizeCompany(company);

        return new Response().ok("Login Success", "token", token);

    }

    public Object register(CompanyRegisterReq request) throws BaseException {
        if (request.getEmail().isBlank()) {
            throw CompanyException.emailNull();
        }
        if (request.getCompanyName().isBlank()) {
            throw CompanyException.nameNull();
        }
        if (request.getPassWord().isBlank()) {
            throw CompanyException.passwordNull();
        }
        if (request.getPhone().isBlank()) {
            throw CompanyException.phoneNull();
        }
        if (request.getType().isBlank()) {
            throw CompanyException.typeNull();
        }
        companyService.register(request.getEmail(), request.getCompanyName(), request.getPassWord(), request.getPhone(), request.getType());
        return new Response().success("Register Success");
    }

    public Object editCompanyProfile(CompanyEditReq request) throws BaseException {
        Company companyByIdToken = tokenService.getCompanyByIdToken();
        if (request.getCompanyName().isBlank()) {
            throw CompanyException.nameNull();
        }
        if (request.getPhone().isBlank()) {
            throw CompanyException.phoneNull();
        }
        if (request.getType().isBlank()) {
            throw CompanyException.typeNull();
        }
        companyService.editCompany(companyByIdToken, request.getCompanyName(), request.getPhone(), request.getType());
        return new Response().success("แก้ไขสำเร็จ");
    }

    public Object changePassword(CompanyChangePassword request) throws BaseException {
        Company companyByIdToken = tokenService.getCompanyByIdToken();
        if (!companyService.matchPassword(request.getOldPassWord(), companyByIdToken.getPassWord())) {
            throw CompanyException.passwordIncorrect();
        }
        if (request.getNewPassWord().isBlank()) {
            throw CompanyException.passwordNull();
        }
        if (companyService.matchPassword(request.getNewPassWord(), companyByIdToken.getPassWord())){
            throw CompanyException.passwordDuplicated();
        }
        companyService.changePassword(companyByIdToken, request.getNewPassWord());
        return new Response().success("ChangePassword Success");
    }

    public Object companyForgetPassword(CompanyForgetPassword request) throws CompanyException, MessagingException {

        Optional<Company> byEmail = companyService.findByEmail(request.getEmail());
        if (byEmail.isEmpty()) {
            throw CompanyException.emailNull();
        }
        sentEmailService.companyForgetPassword(request.getEmail());
        return new Response().success("Company SentResetPasswordEmail Success");
    }

    public Object companyResetPassword(CompanyResetPassword request) throws BaseException {
        Company companyByIdToken = tokenService.getCompanyByIdToken();
        if (request.getNewPassWord().isBlank()) {
            throw CompanyException.passwordNull();
        }
        if (request.getConfirmPassWord().isBlank()) {
            throw CompanyException.passwordNull();
        }
        if (!request.getNewPassWord().equals(request.getConfirmPassWord())) {
            throw CompanyException.passwordIncorrect();
        }

        companyService.resetPassword(companyByIdToken, request.getNewPassWord());
        return new Response().success("ResetPassword Success");
    }

    public Object changeEmail(CompanyEmailReq request) throws BaseException, MessagingException {

        Company companyByIdToken = tokenService.getCompanyByIdToken();
        if (companyByIdToken.getEmail().equals(request.getEmail())) {
            throw CompanyException.emailVerified();
        }
        companyService.changeEmail(companyByIdToken, request.getEmail());
        sentEmailService.companyVerifyEmail(companyByIdToken, request.getEmail());
        return new Response().success("ChangeEmail Success");
    }

    public Object sentVerifyEmail() throws BaseException, MessagingException {
        Company companyByIdToken = tokenService.getCompanyByIdToken();
        sentEmailService.companyVerifyEmail(companyByIdToken, companyByIdToken.getEmail());
        return new Response().success("Sent VerifyEmail Success");
    }

    public Object VerifyEmail() throws BaseException {
        Company companyByIdToken = tokenService.getCompanyByIdToken();
        companyService.verifyEmail(companyByIdToken, companyByIdToken.getNewEmail());
        return new Response().success("Verified Email Success");
    }

}