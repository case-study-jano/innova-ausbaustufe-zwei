package io.mesoneer.repositories;

import io.mesoneer.entities.InnovationProposal;
import io.mesoneer.enums.ProposalStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface InnovationProposalRepository extends CrudRepository<InnovationProposal, Long> {
    @Modifying
    @Query("UPDATE InnovationProposal i SET i.status = :status WHERE i.id = :id")
    void updateInnovationProposalStatusById(@Param("id") Long id, @Param("status") ProposalStatus status);
}
