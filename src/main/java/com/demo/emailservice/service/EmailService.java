package com.demo.emailservice.service;

import com.demo.emailservice.model.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    private static final String TEMPLATE_NAME = "REGISTRATION";
    private static final String SPRING_LOGO_IMAGE = "templates/images/spring.png";
    private static final String PNG_MIME = "image/png";
    private static final String MAIL_SUBJECT = "Seja bem vindo(a):";

    @Value("${spring.mail.properties.mail.smtp.from}")
    private String mailFrom;

    @Value("${mail.from.name:Identity}")
    private String mailFromName;

    @Value("${spring.mail.default-encoding:UTF-8}")
    private String emailEncoding;

    private final JavaMailSender mailSender;
    private final TemplateEngine htmlTemplateEngine;

    public EmailService(JavaMailSender mailSender, TemplateEngine htmlTemplateEngine) {
        this.mailSender = mailSender;
        this.htmlTemplateEngine = htmlTemplateEngine;
    }

    public void sendMailWithInline(User user) {
        try {
            String confirmationUrl = "generated_confirmation_url";
            String logoPath = "templates/images/spring.png";

            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            final MimeMessageHelper email;
            email = new MimeMessageHelper(mimeMessage, true, emailEncoding);

            email.setTo(user.getEmail());
            email.setSubject(MAIL_SUBJECT);
            email.setFrom(new InternetAddress(mailFrom, mailFromName));


            Map<String, Object> variables = new HashMap<>();
            variables.put("email", user.getEmail());
            variables.put("name", user.getName());
            variables.put("springLogo", logoPath);
            variables.put("url", confirmationUrl);

            final Context ctx = new Context(LocaleContextHolder.getLocale());
            ctx.setVariables(variables);

            final String htmlContent = this.htmlTemplateEngine.process(TEMPLATE_NAME, ctx);
            email.setText(htmlContent, true);

            ClassPathResource clr = new ClassPathResource(logoPath);
            email.addInline("springLogo", clr, PNG_MIME);

            mailSender.send(mimeMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {
            System.err.println("Erro ao enviar e-mail: " + e.getMessage());

        }
    }
}
