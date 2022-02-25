package projectA.projectA.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
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

    public SentEmailService(JavaMailSender javaMailSender, TokenService tokenService, UserService userService) {
        this.javaMailSender = javaMailSender;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    public void forgetPassword(String toEmail) throws MessagingException {

        Optional<User> byEmail = userService.findByEmail(toEmail);
        User user = byEmail.get();
        String token = tokenService.tokenizeForgetPassword(user);
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

    public void varifyEmail(String email) throws MessagingException {
        Optional<User> byEmail = userService.findByEmail(email);
        User user = byEmail.get();
        String token = tokenService.tokenize(user);
        int verify = 1;
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
}
