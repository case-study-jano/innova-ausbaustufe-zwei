package io.mesoneer.delegates;

import io.mesoneer.services.EmailService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendProposalApprovedEmailSupervisorDelegate implements JavaDelegate {

    private final EmailService emailService;

    @Autowired
    public SendProposalApprovedEmailSupervisorDelegate(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        emailService.sendProposalApprovedMessageToSupervisor();
    }
}
