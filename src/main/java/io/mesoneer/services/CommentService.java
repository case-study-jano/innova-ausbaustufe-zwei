package io.mesoneer.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mesoneer.entities.Comment;
import io.mesoneer.entities.InnovationProposal;
import io.mesoneer.repositories.CommentRepository;
import io.mesoneer.repositories.InnovationProposalRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final InnovationProposalRepository proposalRepository;
    private final CommentRepository commentRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public CommentService(InnovationProposalRepository proposalRepository, CommentRepository commentRepository) {
        this.proposalRepository = proposalRepository;
        this.commentRepository = commentRepository;
    }

    public void createComment(Long proposalId, String commentText, String commenter) {
        InnovationProposal proposal = proposalRepository.findById(proposalId).orElseThrow(NotFoundException::new);

        Comment comment = new Comment(commentText, proposal, commenter);

        commentRepository.save(comment);
    }

    public String getAllCommentsInJSON(Long proposalId) throws JsonProcessingException {
        List<Comment> comments = commentRepository.findByProposalId(proposalId);

        return objectMapper.writeValueAsString(comments);
    }
}
