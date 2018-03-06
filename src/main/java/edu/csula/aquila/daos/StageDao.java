package edu.csula.aquila.daos;

import edu.csula.aquila.model.Timeline.Stage;

public interface StageDao {
	Stage getStage(Long id);

	Stage saveStage(Stage stage);

	void deleteStage(Long id );
	
}
