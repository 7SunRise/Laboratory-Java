package com.test.storetransactionsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

//-------------------------------------------------------------------------------------------------------

public class TransactionCreateController implements Stageable {
    private Stage stage;
    private Stageable parent;
    
    @FXML
    private ComboBox<String> SellersComboBox;

    @FXML
    private ComboBox<String> ProductsComboBox;

    @FXML
    private TextArea DateTextArea;

    @FXML
    private Button okButton;

    @Override
    public void setStage(Stage stage, Stageable parent) {            // установка сцены
		this.stage = stage;
        this.parent = parent;
	}

    public void onOkButtonClick() throws IOException, SQLException {            // создание новой транзакции при нажатии на кнопку
        if (SellersComboBox.getValue() != null && ProductsComboBox.getValue() != null && !DateTextArea.getText().isEmpty()) {
            DBAdapter.insertTransaction(Integer.valueOf(ProductsComboBox.getValue().split("\\.")[0]), Integer.valueOf(SellersComboBox.getValue().split("\\.")[0]), DateTextArea.getText());
            Stage stage = (Stage)okButton.getScene().getWindow();
            stage.close();
            parent.updateTable();
        }
    }

    private void fillComboBoxesSellers() throws IOException, SQLException {          // заполняем бокс вариантами выбора продавцов
        ObservableList<String> Sellers = FXCollections.observableArrayList();
        for (Seller seller : DBAdapter.selectSellers()) {
            Sellers.add(seller.getId() + ". " + seller.getSurname());
        }
        SellersComboBox.setItems(Sellers);
    }

    private void fillComboBoxesProducts() throws IOException, SQLException {          // заполняем бокс вариантами выбора продуктов
        ObservableList<String> Products = FXCollections.observableArrayList();
        for (Product product : DBAdapter.selectProducts()) {
            Products.add(product.getId() + ". " + product.getModel());
        }
        ProductsComboBox.setItems(Products);
    }

    @Override 
    public void updateTable() throws IOException, SQLException {     // нужен так как имлементит stageable
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {          // инициализатор окна
        try {
            fillComboBoxesSellers();
            fillComboBoxesProducts();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}