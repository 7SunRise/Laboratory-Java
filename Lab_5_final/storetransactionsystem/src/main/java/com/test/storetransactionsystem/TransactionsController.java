package com.test.storetransactionsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

//-----------------------------------------------------------------------------------------

public class TransactionsController implements Stageable {
    private Stage stage;
    private Stageable parent;

    @FXML
    private TableView<Transaction> tableTransactions;

    @FXML
    private TableColumn<Transaction, Integer> idCol;
    
    @FXML
    private TableColumn<Transaction, String> ModelCol;
    
    @FXML
    private TableColumn<Transaction, String> SellerCol;
    
    @FXML
    private TableColumn<Transaction, Double> DateCol;

    @FXML
    public void handleDelete() throws IOException, SQLException {          // удаление транзакции по нажатию правой клавиши мыши
        Transaction transaction = tableTransactions.getSelectionModel().getSelectedItem();
        DBAdapter.deleteTransaction(transaction.getId());
        updateTable();
    }

    @FXML
    public void onTransactionCreateButtonClick() throws IOException {       // открытие нового окна при нажатии на кнопку добавления транзакции
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(StoreTransactionSystem.class.getResource("transaction_create_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1500, 750);
        Stageable controller = fxmlLoader.getController();
        controller.setStage(newStage, (Stageable)this);
        newStage.setTitle("Transaction creation");
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    public void onSellerButtonClick() throws IOException {         // открытие нового окна при нажатии на кнопку перехода на таблицу продавцов
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(StoreTransactionSystem.class.getResource("sellers_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1500, 750);
        newStage.setTitle("Sellers setting");
        newStage.setScene(scene);
        newStage.show();
    }

    public void onProductButtonClick() throws IOException {         // открытие нового окна при нажатии на кнопку перехода на таблицу продуктов
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(StoreTransactionSystem.class.getResource("products_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1500, 750);
        newStage.setTitle("Products setting");
        newStage.setScene(scene);
        newStage.show();
    }

    @Override
    public void setStage(Stage stage, Stageable parent) {          // установка сцены
        this.stage = stage;
        tableTransactions.prefWidthProperty().bind(this.stage.widthProperty());
        tableTransactions.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.parent = parent;
    }

    @Override
    public void updateTable() throws IOException, SQLException {          // обновление таблицы
        ArrayList<Transaction> data = DBAdapter.selectTransactions();
        ObservableList<Transaction> data_new = FXCollections.observableArrayList(data);
        tableTransactions.setItems(data_new);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {       // инициализация
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        ModelCol.setCellValueFactory(new PropertyValueFactory<>("Product"));
        SellerCol.setCellValueFactory(new PropertyValueFactory<>("Seller"));
        DateCol.setCellValueFactory(new PropertyValueFactory<>("Date"));

        try {
            updateTable();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}