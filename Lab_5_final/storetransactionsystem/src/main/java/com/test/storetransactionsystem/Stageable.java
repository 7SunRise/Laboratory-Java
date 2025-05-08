package com.test.storetransactionsystem;

import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.Initializable;

//--------------------------------------------------------------------------------------------------------

public interface Stageable extends Initializable {     // интерфейс для контроллеров
	
	void setStage(Stage stage, Stageable parent);

	void updateTable() throws IOException, SQLException;
}
