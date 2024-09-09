package io.mesoneer.delegates;

import io.mesoneer.services.CommentService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProcessSupervisorResponseDelegate implements JavaDelegate {

    private final CommentService commentService;

    @Autowired
    public ProcessSupervisorResponseDelegate(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String supervisorDecision = (String) delegateExecution.getVariable("supervisorProposalDecision");

        if (Objects.equals(supervisorDecision, "questioned") || Objects.equals(supervisorDecision, "rejected")) {
            String supervisorComment = (String) delegateExecution.getVariable("supervisorComment");
            String supervisorName = (String) delegateExecution.getVariable("supervisorName");
            Long proposalId = (Long) delegateExecution.getVariable("proposalId");

            commentService.createComment(proposalId, supervisorComment, supervisorName);

            String serializedComments = commentService.getAllCommentsInJSON(proposalId);

            delegateExecution.setVariable("comments", serializedComments);
        }
    }
}
