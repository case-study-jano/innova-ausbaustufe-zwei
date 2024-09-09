package io.mesoneer.repositories;

import io.mesoneer.entities.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByProposalId(Long proposalId);
}
