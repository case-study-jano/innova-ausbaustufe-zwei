package io.mesoneer.delegates;

import io.mesoneer.entities.InnovationProposal;
import io.mesoneer.repositories.InnovationProposalRepository;
import jakarta.ws.rs.NotFoundException;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateProposalDelegate implements JavaDelegate {

    private final InnovationProposalRepository proposalRepository;

    @Autowired
    public UpdateProposalDelegate(InnovationProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String title = (String) delegateExecution.getVariable("title");
        String description = (String) delegateExecution.getVariable("description");
        Long proposalId = (Long) delegateExecution.getVariable("proposalId");
        InnovationProposal proposal = proposalRepository.findById(proposalId).orElseThrow(NotFoundException::new);

        proposal.setTitle(title);
        proposal.setDescription(description);

        proposalRepository.save(proposal);
    }
}
