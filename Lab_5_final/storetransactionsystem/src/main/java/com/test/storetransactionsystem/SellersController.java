package com.test.storetransactionsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

//-----------------------------------------------------------------------------------------------------

public class SellersController implements Stageable {
    private Stage stage;
    private Stageable parent;
    
    @FXML
    private TextField txtSurname;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSecond_name;

    @FXML
    private TableView<Seller> tableSellers;

    @FXML
    private TableColumn<Transaction, Integer> idCol;
    
    @FXML
    private TableColumn<Transaction, String> SurnameCol;

    @FXML
    private TableColumn<Transaction, String> NameCol;

    @FXML
    private TableColumn<Transaction, String> Second_nameCol;

    @Override
    public void setStage(Stage stage, Stageable parant) {
		this.stage = stage;
        this.parent = parent;
	}

    @FXML
    public void onAddButtonClick() throws IOException, SQLException {            // добавление продавца при нажатии на кнопку
        String Surname = txtSurname.getText();
        String Name = txtName.getText();
        String Second_name = txtSecond_name.getText();
        if (!Surname.isEmpty() && !Name.isEmpty() && !Second_name.isEmpty()) {
            DBAdapter.insertNewSeller(Surname, Name, Second_name);
        }
        updateTable();
    }

    @FXML
    public void onUpdateButtonClick() throws IOException, SQLException {           // обновление продавца при нажатии на кнопку
        Seller Seller = tableSellers.getSelectionModel().getSelectedItem();
        if (Seller != null && !txtSurname.getText().isEmpty() && !txtName.getText().isEmpty() && !txtSecond_name.getText().isEmpty()) {
            DBAdapter.updateSeller(Seller.getId(), txtSurname.getText(), txtName.getText(), txtSecond_name.getText());
            updateTable();
        }
    }

    @FXML
    public void onDeleteButtonClick() throws IOException, SQLException {        //  удаление продавца при нажатии на кнопку
        Seller Seller = tableSellers.getSelectionModel().getSelectedItem();
        if (Seller != null) {
            DBAdapter.deleteSeller(Seller.getId());
            updateTable();
        }
    }

    @Override
    public void updateTable() throws IOException, SQLException {      // обновить данные в таблице продавцов
        ArrayList<Seller> data = DBAdapter.selectSellers();
        ObservableList<Seller> data_new = FXCollections.observableArrayList(data);
        tableSellers.setItems(data_new);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {           // инициализация таблицы
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        SurnameCol.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        NameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Second_nameCol.setCellValueFactory(new PropertyValueFactory<>("Second_name"));

        try {
            updateTable();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    

}