package io.mesoneer.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    @ManyToOne
    private InnovationProposal proposal;

    private String commenter;


    public Comment() {}

    public Comment(String text, InnovationProposal proposal, String commenter) {
        this.text = text;
        this.proposal = proposal;
        this.commenter = commenter;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public InnovationProposal getProposal() {
        return proposal;
    }

    public void setProposal(InnovationProposal proposal) {
        this.proposal = proposal;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }
}
