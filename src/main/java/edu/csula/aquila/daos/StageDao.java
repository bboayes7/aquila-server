package edu.csula.aquila.daos;

import java.util.List;

import edu.csula.aquila.model.Timeline.Stage;

public interface StageDao {
	
	public Stage getStage( Long id );
	
	public Stage saveStage( Stage stage );
	
	public Stage updateStage( Stage stage );
	
	public void deleteStage( Long id );
	
	public List<Stage> getStages( Long timelineId );

	
}
