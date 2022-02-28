package projectA.projectA.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import projectA.projectA.entity.Company;
import projectA.projectA.entity.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;

@Service
public class SentEmailService {

    @Value("${Spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender javaMailSender;
    private final TokenService tokenService;
    private final UserService userService;
    private final CompanyService companyService;

    public SentEmailService(JavaMailSender javaMailSender, TokenService tokenService, UserService userService, CompanyService companyService) {
        this.javaMailSender = javaMailSender;
        this.tokenService = tokenService;
        this.userService = userService;
        this.companyService = companyService;
    }

    public void userForgetPassword(String toEmail) throws MessagingException {

        Optional<User> byEmail = userService.findByEmail(toEmail);
        User user = byEmail.get();
        String token = tokenService.tokenizeUserForgetPassword(user);
        String subject = "ResetPassword Project A";
        String body = "<a href=\"http://localhost:4200/reset-password/"+ token + "\">ResetPassWord</a>";

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body,true);

        javaMailSender.send(mimeMessage);

        System.out.println("Mail Sent Success...");
    }

    public void userVerifyEmail(String email) throws MessagingException {
        Optional<User> byEmail = userService.findByEmail(email);
        User user = byEmail.get();
        String token = tokenService.tokenize(user);
        String subject = "VerifyEmail Project A";
        String body = "<a href=\"http://localhost:4200/verify-email/"+ token + "\">VerifyEmail!</a>";

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        messageHelper.setFrom(fromEmail);
        messageHelper.setTo(user.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(body,true);

        javaMailSender.send(mimeMessage);

        System.out.println("VerifyEmail Sent Success...");

    }

    public void companyForgetPassword(String toEmail) throws MessagingException {

        Optional<Company> byEmail = companyService.findByEmail(toEmail);
        Company company = byEmail.get();
        String token = tokenService.tokenizeCompanyForgetPassword(company);
        String subject = "ResetPassword Project A";
        String body = "<a href=\"http://localhost:4200/reset-password/"+ token + "\">ResetPassWord</a>";

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body,true);

        javaMailSender.send(mimeMessage);

        System.out.println("Mail Sent Success...");
    }

    public void companyVerifyEmail(Company company,String toEmail) throws MessagingException {

        String token = tokenService.tokenizeCompany(company);
        String subject = "VerifyEmail Project A";
        String body = "<a href=\"http://localhost:4200/verify-email/"+ token + "\">VerifyEmail!</a>";

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        messageHelper.setFrom(fromEmail);
        messageHelper.setTo(toEmail);
        messageHelper.setSubject(subject);
        messageHelper.setText(body,true);

        javaMailSender.send(mimeMessage);

        System.out.println("VerifyEmail Sent Success...");

    }
}
