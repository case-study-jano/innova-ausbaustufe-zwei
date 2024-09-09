package io.mesoneer.delegates;

import io.mesoneer.enums.ProposalStatus;
import io.mesoneer.repositories.InnovationProposalRepository;
import io.mesoneer.services.CommentService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProcessInnovationBoardResponseDelegate implements JavaDelegate {

    private final CommentService commentService;
    private final InnovationProposalRepository innovationProposalRepository;

    @Autowired
    public ProcessInnovationBoardResponseDelegate(CommentService commentService, InnovationProposalRepository innovationProposalRepository) {
        this.commentService = commentService;
        this.innovationProposalRepository = innovationProposalRepository;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String boardProposalDecision = (String) delegateExecution.getVariable("boardProposalDecision");
        Long proposalId = (Long) delegateExecution.getVariable("proposalId");

        if (Objects.equals(boardProposalDecision, "questioned")) {
            String boardComment = (String) delegateExecution.getVariable("boardComment");
            String boardMemberName = (String) delegateExecution.getVariable("boardMemberName");

            commentService.createComment(proposalId, boardComment, boardMemberName);

            String serializedComments = commentService.getAllCommentsInJSON(proposalId);

            delegateExecution.setVariable("comments", serializedComments);
        } else {
            innovationProposalRepository.updateInnovationProposalStatusById(proposalId, ProposalStatus.APPROVED);
        }
    }
}
