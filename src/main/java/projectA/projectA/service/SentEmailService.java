package projectA.projectA.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import projectA.projectA.entity.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;

@Service
public class SentEmailService {

    private final JavaMailSender javaMailSender;
    private final TokenService tokenService;
    private final UserService userService;

    public SentEmailService(JavaMailSender javaMailSender, TokenService tokenService, UserService userService) {
        this.javaMailSender = javaMailSender;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    public void forgetPassword(String toEmail,String subject) throws MessagingException {

        Optional<User> byEmail = userService.findByEmail(toEmail);
        User user = byEmail.get();
        String token = tokenService.tokenizeForgetPassword(user);
        String body = "<a href=\"http://localhost:4200/reset-password?id="+ token + "\">ResetPassWord</a>";

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
        message.setFrom("krissakorn6969@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body,true);

        javaMailSender.send(mimeMessage);

        System.out.println("Mail Sent Success...");
    }
}
