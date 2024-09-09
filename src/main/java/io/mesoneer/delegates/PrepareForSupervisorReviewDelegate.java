package io.mesoneer.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class PrepareForSupervisorReviewDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.removeVariable("supervisorProposalDecision");
        delegateExecution.removeVariable("supervisorComment");
    }
}
