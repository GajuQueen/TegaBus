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
    String subject = "Verify Your Tegabus Account";
    String verificationUrl = "http://localhost:8080/api/auth/verify-email?token=" + token;

    String body = "<h3>Welcome to Tegabus!</h3>" +
            "<p>Please click the link below to verify your email address:</p>" +
            "<a href=\"" + verificationUrl + "\">Verify Email</a>" +
            "<p>If you did not register, please ignore this email.</p>";

    try {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(body, true);
        mailSender.send(message);
    } catch (Exception e) {
        throw new RuntimeException(" Failed to send verification email: " + e.getMessage());
    }
}
}