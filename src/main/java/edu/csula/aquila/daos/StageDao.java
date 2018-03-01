package edu.csula.aquila.daos;

import edu.csula.aquila.model.Timeline.Stage;

public interface StageDao {
	
	public Stage getStage( Long id );
	
	public Stage saveStage( Stage stage );
	
	public void removeStage( Long id );

}
