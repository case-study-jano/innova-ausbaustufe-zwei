package io.mesoneer.delegates;

import io.mesoneer.enums.ProposalStatus;
import io.mesoneer.repositories.InnovationProposalRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RejectProposalDelegate implements JavaDelegate {

    private final InnovationProposalRepository innovationProposalRepository;

    @Autowired
    public RejectProposalDelegate(InnovationProposalRepository innovationProposalRepository) {
        this.innovationProposalRepository = innovationProposalRepository;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long proposalId = (Long) delegateExecution.getVariable("proposalId");
        innovationProposalRepository.updateInnovationProposalStatusById(proposalId, ProposalStatus.REJECTED);
    }
}
