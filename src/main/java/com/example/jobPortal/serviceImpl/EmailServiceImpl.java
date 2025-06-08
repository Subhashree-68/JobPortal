package com.example.jobPortal.serviceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.jobPortal.Entity.User;
import com.example.jobPortal.Enum.Role;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl {
	@Value("${app.url}")
	private String applicationUrl;
	@Autowired
	private JavaMailSender javaMailSender;
	private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

	public void sendEmailForRegisterUser(User user) {Runnable emailTask = () -> {
	    try {
	        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	        
	        String sendEmail = user.getEmail();
	        String subject = "Welcome to JobPortal System";
	        String userName = user.getUserName();
	        Role role = user.getRole();
	        String applicationUrl = "https://your-application-url.com"; 
	        
	        String content = String.format("Dear %s,<br><br>"
	                + "We are pleased to welcome you to <b>%s</b>. Your account has been successfully created, "
	                + "and you can now log in to the system to manage your tasks efficiently. Below are your login credentials:<br><br>"
	                + "<b>Username:</b> %s<br>"
	                + "<b>Role:</b> %s<br><br>"
	                + "<b>Next Steps:</b><br>"
	                + "- Explore the platform and features tailored to your role.<br>"
	                + "- Refer to our <a href='%s'>User Guide/Help Center</a> for assistance.<br>"
	                + "- If you encounter any issues, feel free to contact us at <a href='mailto:%s'>%s</a>.<br><br>"
	                + "<b>Note:</b> If you did not register for this account, please notify us immediately.<br><br>"
	                + "We look forward to supporting you in managing your tasks effectively.<br><br>"
	                + "Best regards,<br>"
	                + "%s Support Team<br>"
	                + "<a href='%s'>%s</a>", 
	                userName, "JobPortal System", userName, role,
	                applicationUrl, "support@watsoo.com", "support@watsoo.com",
	                "JobPortal", applicationUrl, "JobPortal");

	        helper.setTo(sendEmail);
	        helper.setSubject(subject);
	        helper.setText(content, true);
	        javaMailSender.send(mimeMessage);

	        log.info("Email sent successfully to user: {}", user.getEmail());
	    } catch (Exception e) {
	        log.error("Failed to send email to user: {}", user.getEmail(), e);
	    }
	};

	Thread emailThread = new Thread(emailTask);
	emailThread.setPriority(Thread.NORM_PRIORITY);
	emailThread.start();
}

}
