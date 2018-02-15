package edu.csula.aquila.daos;

import java.util.List;

import edu.csula.aquila.model.Proposal;

public interface ProposalDao {
	
	Proposal getProposal( Long id );
	
	Proposal saveProposal( Proposal proposal);
	
	//Proposal updateProposal( Proposal proposal );

	List<Proposal> getProposalsOfUser( Long id );
	

}
