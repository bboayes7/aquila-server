package edu.csula.aquila.daos;

import edu.csula.aquila.model.Timeline.Stage;

public interface StageDao {
	Stage getStage(Long id);

	Stage createStage(Stage stage);

	Stage updateStage(Stage stage);

	Stage deleteStage(Stage stage);
	
}
