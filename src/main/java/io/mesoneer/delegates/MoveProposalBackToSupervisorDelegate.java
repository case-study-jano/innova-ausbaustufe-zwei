package io.mesoneer.delegates;

import io.mesoneer.enums.ProposalStatus;
import io.mesoneer.repositories.InnovationProposalRepository;
import io.mesoneer.services.CommentService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MoveProposalBackToSupervisorDelegate implements JavaDelegate {

    private final InnovationProposalRepository innovationProposalRepository;
    private final CommentService commentService;

    @Autowired
    public MoveProposalBackToSupervisorDelegate(InnovationProposalRepository innovationProposalRepository, CommentService commentService) {
        this.innovationProposalRepository = innovationProposalRepository;
        this.commentService = commentService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long proposalId = (Long) delegateExecution.getVariable("proposalId");
        innovationProposalRepository.updateInnovationProposalStatusById(proposalId, ProposalStatus.SUPERVISOR_REVIEW);

        delegateExecution.removeVariable("boardProposalDecision");
        delegateExecution.removeVariable("boardComment");
        delegateExecution.removeVariable("supervisorProposalDecision");
        delegateExecution.removeVariable("supervisorComment");

        String serializedComments = commentService.getAllCommentsInJSON(proposalId);

        delegateExecution.setVariable("comments", serializedComments);
    }
}
