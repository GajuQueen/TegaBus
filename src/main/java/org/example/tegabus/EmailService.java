package org.example.tegabus;

import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;


    public void sendEmailWithQRCode(String toEmail, String subject, String bodyText, byte[] qrCodeImage) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(bodyText, true);

        helper.addInline("qrCodeImage", new ByteArrayResource(qrCodeImage), "image/png");
        mailSender.send(message);
    }

//    VERIFICATION
    public void sendVerificationEmail(String toEmail, String token) {
        String subject = "Verify your Tegabus Account";
        String body = "Click this link to verify your email:<br><br>" +
                "<a href=\"http://localhost:8080/api/auth/verify?token=" + token + "\">Verify Email</a>";

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body, true);
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send verification email");
        }
    }
}