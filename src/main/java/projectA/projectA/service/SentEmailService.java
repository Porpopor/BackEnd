package projectA.projectA.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SentEmailService {

    private final JavaMailSender javaMailSender;

    public SentEmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void forgetPassword(String toEmail,String subject, String body) throws MessagingException {
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
