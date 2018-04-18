package edu.csula.aquila.controller.jwt;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.FileInfoDao;
import edu.csula.aquila.daos.IntakeDao;
import edu.csula.aquila.daos.ProposalDao;
import edu.csula.aquila.daos.UserDao;
import edu.csula.aquila.error.RestException;
import edu.csula.aquila.model.FileInfo;
import edu.csula.aquila.model.IntakeForm;
import edu.csula.aquila.model.Proposal;
import edu.csula.aquila.model.Proposal.Status;
import edu.csula.aquila.model.User.Type;
import edu.csula.aquila.model.Stage;
import edu.csula.aquila.model.Timeline;
import edu.csula.aquila.model.User;
import edu.csula.aquila.model.User.Type;

@RestController
public class ProposalController {

	@Autowired
	private ProposalDao proposalDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private IntakeDao intakeDao;
	
	@Autowired
	private FileInfoDao fileInfoDao;
	
	//Get a proposal
	@RequestMapping(value = "proposal/{id}", method = RequestMethod.GET)
	public Proposal getProposal(@ModelAttribute("currentUser") User currentUser, @PathVariable Long id)
	{
		Long userId = currentUser.getId();
		
		if(currentUser.getType() == Type.INVESTIGATOR && !currentUser.getId().equals(userId) )
			throw new RestException(401, "UNAUTHORIZED");
		
		return proposalDao.getProposal(id);
	}
	
	//Create a proposal
	@RequestMapping(value = "proposal/", method = RequestMethod.POST)
	public Proposal newProposal(@RequestBody ProposalInstantiate proposalInstantiate, @ModelAttribute("currentUser") User currentUser) {
		
		//check if the user creating the proposal is creating a proposal for themselves
		if(!proposalInstantiate.getUserId().equals(currentUser.getId()))
			throw new RestException(401, "UNAUTHORIZED");
		
		//create proposal and set the name
		Proposal proposal = new Proposal();
		
		
		proposal.setProposalName(proposalInstantiate.getProposalName());
		
		//set the user
		User user = userDao.getUser(currentUser.getId());
		proposal.setUser(user);
		
		//set the status
		proposal.setStatus(Status.DRAFT);

		//set the date
		proposal.setDateCreated(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		
		
		//create a null intake form
		IntakeForm intakeForm = new IntakeForm();
		intakeForm.setProjectTitle(proposalInstantiate.getProposalName());
		intakeForm.setPrincipleInvestigator(user.getFirstName() + " " + user.getLastName());
		intakeForm = intakeDao.saveIntakeForm(intakeForm);
		proposal.setIntakeForm(intakeForm);
		
		//instantiate timeline with pre meeting stage
		Map<String,Long> forms = new HashMap<>();
		forms.put("Intake Form", intakeForm.getId());
		Map<String,FileInfo> files = new HashMap<>();
		FileInfo preMeetingBudget = new FileInfo("Pre-Meeting Budget", false);
		files.put("Pre-Meeting Budget", fileInfoDao.saveFile(preMeetingBudget));
		Stage preMeetingStage = new Stage(0, "Pre-Meeting", null,"Principal Investigator", forms, files);
		preMeetingStage.setStageOrder(0);
		List<Stage> preMeeting = new ArrayList<>();
		preMeeting.add(preMeetingStage);
		
		Timeline timeline = new Timeline();
		timeline.setProposalName(proposal.getProposalName());
		timeline.setPrincipalInvestigator(user.getFirstName() + " " + user.getLastName());
		timeline.setStages(preMeeting);
		preMeetingStage.setTimeline(timeline);
		timeline.setProposal(proposal);
		proposal.setTimeline(timeline);
		

		
		return proposal = proposalDao.saveProposal(proposal);
	}
	
	//Get a list of proposals of a user
	@RequestMapping(value = "proposals/{userId}", method = RequestMethod.GET)
	public List<Proposal> getProposalsOfUser(@PathVariable Long userId, @ModelAttribute("currentUser") User currentUser){
		if(currentUser.getType() == Type.INVESTIGATOR && !userId.equals(currentUser.getId()))
			throw new RestException(401, "UNAUTHORIZED");

		
		return proposalDao.getProposalsOfUser(userId);
	}
	
	
}

class ProposalInstantiate{
	String proposalName;
	Long userId;
	
	ProposalInstantiate(){
		
	}
	
	public String getProposalName() {
		return proposalName;
	}
	
	public Long getUserId() {
		return userId;
	}
	
}

