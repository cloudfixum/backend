package com.um.cloudfixum.cloudfixum.service;

import com.sun.mail.smtp.SMTPAddressFailedException;
import com.um.cloudfixum.cloudfixum.email.EmailBody;
import com.um.cloudfixum.cloudfixum.email.EmailHtml;
import com.um.cloudfixum.cloudfixum.email.EmailPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;
import java.util.LinkedList;
import java.util.List;

@Service
public class EmailService implements EmailPort {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender sender;
    @Autowired
    private EmailHtml emailHtml;

    public EmailBody createEmail(String content, String email, String subject) {
        return new EmailBody(email, content, subject);
    }

    @Override
    @Async
    public void sendEmail(String content, String email, String subject) {
        EmailBody emailBody = createEmail(content, email, subject);
        email = emailBody.getEmail();
        content = emailBody.getContent();
        subject = emailBody.getSubject();
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setText(emailHtml.generateMailHtml(content), true);
            helper.setSubject(subject);
            List<String> attachment = new LinkedList<>();
            if (!attachment.isEmpty()) {
                addMailAttachments(attachment, helper);
            }
            sender.send(message);
            LOGGER.info("Mail enviado!");
        } catch (SMTPAddressFailedException e) {
            LOGGER.error("Address is incorrect");
        } catch (SendFailedException e) {
            LOGGER.error("Mail dont send");
        } catch (AddressException e) {
            LOGGER.error("Ilegal Character");
        } catch (MessagingException e) {
            LOGGER.error("Error with send mail: {}", e);
        } catch (MailSendException e) {
            LOGGER.error("Dont send email with: {}", e);
        }
    }
        public void addMailAttachments (List < String > attachmentList, MimeMessageHelper helper) throws
        MessagingException {
            for (String attachment_path : attachmentList) {
                FileSystemResource file = new FileSystemResource(attachment_path);
                helper.addAttachment(file.getFilename(), file);
            }

        }

    }
