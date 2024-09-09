package io.mesoneer.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendProposalApprovedMessageToEmployee() {
        String to = "employee@testing.com";
        String subject = "Innovation wurde angenommen";
        String body = "Hey, herzlichen Glückwunsch. Deine Innovation wurde angenommen!";

        sendSimpleMessage(to, subject, body);
    }

    public void sendProposalApprovedMessageToSupervisor() {
        String to = "supervisor@testing.com";
        String subject = "Innovation wurde angenommen";
        String body = "Hey, die Innovation deines Mitarbeiters wurde angenommen.";

        sendSimpleMessage(to, subject, body);
    }

    public void sendReminderEmail() {
        String to = "employee@testing.com";
        String subject = "Reminder";
        String body = "Hey, eine Innovationsanfrage steht noch offen. Bitte kümmere dich möglichst schnell darum.";

        sendSimpleMessage(to, subject, body);
    }

    public void sendSimpleMessage(
            String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@mesoneer.io");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        // mailSender.send(message);  // Needs to be configured properly

        System.out.println("Email sent to " + to + " with subject " + subject);
    }

}
