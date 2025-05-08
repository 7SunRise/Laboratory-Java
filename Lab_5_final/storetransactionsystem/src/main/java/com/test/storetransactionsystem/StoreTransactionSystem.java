package com.test.storetransactionsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

//------------------------------------------------------------------------------------------------------------

public class StoreTransactionSystem extends Application {
    @Override
    public void start(Stage stage) throws IOException {              // инициализация начального окна
        DBAdapter.init();
        FXMLLoader fxmlLoader = new FXMLLoader(StoreTransactionSystem.class.getResource("transactions_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1500, 750);
        stage.setTitle("Store Transaction System");
        stage.setScene(scene);
        stage.show();
        Stageable controller = fxmlLoader.getController();
        controller.setStage(stage, null);
    }

    public static void main(String[] args) {   // запуск
        launch();
    }
}