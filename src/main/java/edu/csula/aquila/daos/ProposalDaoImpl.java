package edu.csula.aquila.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.csula.aquila.model.Proposal;
import edu.csula.aquila.model.User;

@Repository
public class ProposalDaoImpl  implements ProposalDao{

	@PersistenceContext
	private EntityManager entityManager;

	
	@Override
	public Proposal getProposal(Long id) {
		return entityManager.find(Proposal.class, id);
	}

	@Override
	@Transactional
	public Proposal saveProposal(Proposal proposal) {
		proposal = entityManager.merge( proposal );
		
		Proposal condensedProposal = new Proposal();
		condensedProposal.setId(proposal.getId());
		condensedProposal.setProposalName(proposal.getProposalName());
		condensedProposal.setStatus(proposal.getStatus());
		condensedProposal.setDateCreated(proposal.getDateCreated());
		
		return condensedProposal;
	}
	
	@Override
	@Transactional
	public Proposal updateProposal(Proposal proposal)
	{
		return entityManager.merge(proposal);
	}
	
	@Override
	public List<Proposal> getProposalsOfUser( Long id ){
		String query = "from Proposal where user_id = :id";
		return entityManager.createQuery(query, Proposal.class)
				.setParameter("id", id)
				.getResultList();
		
	}

	@Override
	public List<Proposal> getProposals() {
		return entityManager.createQuery( "from Proposal order by proposal_id", Proposal.class )
	            .getResultList();
	}


}


