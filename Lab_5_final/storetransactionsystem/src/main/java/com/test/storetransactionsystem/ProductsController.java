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

public class ProductsController implements Stageable {
    private Stage stage;
    private Stageable parent;
    
    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtYear;

    @FXML
    private TextField txtCountry;

	@FXML
    private TextField txtPrice;

    @FXML
    private TableView<Product> tableProducts;

    @FXML
    private TableColumn<Transaction, Integer> idCol;
    
    @FXML
    private TableColumn<Transaction, String> ModelCol;

    @FXML
    private TableColumn<Transaction, Integer> YearCol;

    @FXML
    private TableColumn<Transaction, String> CountryCol;

	@FXML
    private TableColumn<Transaction, Integer> PriceCol;

    @Override
    public void setStage(Stage stage, Stageable parant) {
		this.stage = stage;
        this.parent = parent;
	}

    @FXML
    public void onAddButtonClick() throws IOException, SQLException {            // добавление товара при нажатии на кнопку
        String Model = txtModel.getText();
        int Year = Integer.valueOf(txtYear.getText());
        String Country = txtCountry.getText();
		int Price = Integer.valueOf(txtPrice.getText());
        if (!txtModel.getText().isEmpty() && !txtYear.getText().isEmpty() && !txtCountry.getText().isEmpty() && !txtPrice.getText().isEmpty()) {
            DBAdapter.insertNewProduct(Model, Year, Country, Price);
        }
        updateTable();
    }

    @FXML
    public void onUpdateButtonClick() throws IOException, SQLException {           // обновление товара при нажатии на кнопку
        Product Product = tableProducts.getSelectionModel().getSelectedItem();
        if (Product != null && !txtModel.getText().isEmpty() && !txtYear.getText().isEmpty() && !txtCountry.getText().isEmpty() && !txtPrice.getText().isEmpty()) {
            DBAdapter.updateProduct(Product.getId(), txtModel.getText(), Integer.valueOf(txtYear.getText()), txtCountry.getText(), Integer.valueOf(txtPrice.getText()));
            updateTable();
        }
    }

    @FXML
    public void onDeleteButtonClick() throws IOException, SQLException {        //  удаление товара при нажатии на кнопку
        Product Product = tableProducts.getSelectionModel().getSelectedItem();
        if (Product != null) {
            DBAdapter.deleteProduct(Product.getId());
            updateTable();
        }
    }

    @Override
    public void updateTable() throws IOException, SQLException {      // обновить данные в таблице товаров
        ArrayList<Product> data = DBAdapter.selectProducts();
        ObservableList<Product> data_new = FXCollections.observableArrayList(data);
        tableProducts.setItems(data_new);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {           // инициализация таблицы
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        ModelCol.setCellValueFactory(new PropertyValueFactory<>("Model"));
        YearCol.setCellValueFactory(new PropertyValueFactory<>("Year_of_production"));
        CountryCol.setCellValueFactory(new PropertyValueFactory<>("Country"));
		PriceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));

        try {
            updateTable();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    

}