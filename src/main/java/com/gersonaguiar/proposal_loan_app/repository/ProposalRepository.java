package com.gersonaguiar.proposal_loan_app.repository;

import com.gersonaguiar.proposal_loan_app.entity.Proposal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalRepository extends CrudRepository<Proposal, Long> {
}
