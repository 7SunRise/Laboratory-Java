package com.test.storetransactionsystem;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

//-------------------------------------------------------------------------------------

public class Transaction {
    private SimpleIntegerProperty id;         // id-транзакции
    private SimpleStringProperty Product;     // купленный товар
    private SimpleStringProperty Seller;      // продавец, осуществивший продажу
    private SimpleStringProperty Date;        // дата осуществления транзакции

    public Transaction(int id, String Product, String Seller, String Date) {   // конструктор для транзакций
        this.id = new SimpleIntegerProperty(id);
        this.Product = new SimpleStringProperty(Product);
        this.Seller = new SimpleStringProperty(Seller);
        this.Date = new SimpleStringProperty(Date);
    }

    public int getId() {            //
        return id.get();            //
    }                               //
                                    //  геттер и сеттер для id-транзакции
    public void setId(int idin) {   //
        id.set(idin);               //
    }                               //

    public String getProduct() {              //
        return Product.get();                 //
    }                                         //
                                              // геттер и сеттер для названия продукта
    public void setProduct(String product) {  //
        this.Product.set(product);            //
    }                                         //

    public String getSeller() {                   //
        return Seller.get();                      //
    }                                             //
                                                  // геттер и сеттер для фамилии продавца
    public void setSeller(String seller) {        //
        this.Seller.set(seller);                  //
    }                                             //
    
    public String getDate() {           //
        return Date.get();              //
    }                                   //
                                        //  геттер и сеттер для даты покупки
    public void setDate(String date) {  //
        this.Date.set(date);            //
    }                                   //
}