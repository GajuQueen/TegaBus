package org.example.tegabus;

import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

//import javax.mail.internet.MimeMessage;
@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    // Send email with QR code as inline image or attachment
    public void sendEmailWithQRCode(String toEmail, String subject, String bodyText, byte[] qrCodeImage) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(bodyText, true);

        helper.addInline("qrCodeImage", new ByteArrayResource(qrCodeImage), "image/png");
        mailSender.send(message);
    }
}