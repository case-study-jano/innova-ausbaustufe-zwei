package io.mesoneer.delegates;

import io.mesoneer.entities.InnovationProposal;
import io.mesoneer.enums.ProposalStatus;
import io.mesoneer.repositories.InnovationProposalRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveProposalDelegate implements JavaDelegate {

    private final InnovationProposalRepository proposalRepository;

    @Autowired
    public SaveProposalDelegate(InnovationProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String creatorName = (String) delegateExecution.getVariable("creatorName");
        String title = (String) delegateExecution.getVariable("title");
        String description = (String) delegateExecution.getVariable("description");

        InnovationProposal innovationProposal = new InnovationProposal(creatorName, title, description, ProposalStatus.SUPERVISOR_REVIEW);

        proposalRepository.save(innovationProposal);

        delegateExecution.setVariable("proposalId", innovationProposal.getId());
    }
}
