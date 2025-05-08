package com.test.storetransactionsystem;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

//---------------------------------------------------------------------------------

public class Product {
    private SimpleIntegerProperty id;                  // id товара
    private SimpleStringProperty Model;                // модель товара
    private SimpleIntegerProperty Year_of_production;  // год производства
    private SimpleStringProperty Country;              // страна производства
    private SimpleIntegerProperty Price;               // цена

    public Product(int id, String Model, int Year_of_production, String Country, int Price) {   // конструктор для товара
        this.id = new SimpleIntegerProperty(id);
        this.Model = new SimpleStringProperty(Model);
        this.Year_of_production = new SimpleIntegerProperty(Year_of_production);
        this.Country = new SimpleStringProperty(Country);
        this.Price = new SimpleIntegerProperty(Price);
    }

    public int getId() {             //
        return id.get();             //
    }                                //
                                     // геттер и сеттер для id транзакции
    public void setId(int idin) {    //
        id.set(idin);                //
    }                                //

    public String getModel() {              //
        return Model.get();                 //
    }                                       //
                                            // геттер и сеттер для модели товара
    public void setModel(String Model) {    //
        this.Model.set(Model);              //
    }                                       //

    public int getYear_of_production() {                            //
        return Year_of_production.get();                            //
    }                                                               //
                                                                    // геттер и сеттер для года производства
    public void setYear_of_production(int Year_of_production) {     //
        this.Year_of_production.set(Year_of_production);            //
    }                                                               //
    
    public String getCountry() {                    //
        return Country.get();                       //
    }                                               //
                                                    // геттер и сеттер для страны производства
    public void setCountry(String Country) {        //
        this.Country.set(Country);                  //
    }                                               //

    public int getPrice() {                    //
        return Price.get();                    //
    }                                          //
                                               // геттер и сеттер для цены товара
    public void setPrice(int Price) {          //
        this.Price.set(Price);                 //
    }                                          //
}