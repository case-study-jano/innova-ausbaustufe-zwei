package io.mesoneer.entities;

import io.mesoneer.enums.ProposalStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class InnovationProposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String creatorName;
    private String title;
    private String description;
    private Date creationDate;
    private ProposalStatus status;

    @OneToMany
    private List<Comment> comments;

    public InnovationProposal() {}

    public InnovationProposal(String creatorName, String title, String description, ProposalStatus status) {
        this.creatorName = creatorName;
        this.title = title;
        this.description = description;
        this.creationDate = new Date();
        this.status = status;
        this.comments = new ArrayList<>();
    }

    public InnovationProposal(String creatorName, String title, String description, Date creationDate, ProposalStatus status, List<Comment> comments) {
        this.creatorName = creatorName;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.status = status;
        this.comments = comments;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public ProposalStatus getStatus() {
        return status;
    }

    public void setStatus(ProposalStatus status) {
        this.status = status;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
