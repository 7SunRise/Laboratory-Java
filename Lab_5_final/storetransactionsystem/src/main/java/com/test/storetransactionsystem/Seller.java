package com.test.storetransactionsystem;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

//-----------------------------------------------------------------------------------------------------------------

public class Seller {  
    private SimpleIntegerProperty id;           // id продавца
    private SimpleStringProperty Surname;       // Фамилия продавца
    private SimpleStringProperty Name;          // Имя продавца
    private SimpleStringProperty Second_name;   // Отчество продавца

    public Seller(int id, String Surname, String Name, String Second_name) {    // конструктор для продавца
        this.id = new SimpleIntegerProperty(id);
        this.Surname = new SimpleStringProperty(Surname);
        this.Name = new SimpleStringProperty(Name);
        this.Second_name = new SimpleStringProperty(Second_name);
    }

    public int getId() {             //
        return id.get();             //
    }                                //
                                     //   геттер и сеттер для id продавца
    public void setId(int idin) {    //
        id.set(idin);                //
    }                                //

    public String getSurname() {                   //
        return Surname.get();                      //
    }                                              //
                                                   //  геттер и сеттер для фамилии продавца
    public void setSurname(String Surname) {       //
        this.Surname.set(Surname);                 //
    }                                              //

    public String getName() {                    //
        return Name.get();                       //
    }                                            //
                                                 //  геттер и сеттер для имени продавца
    public void setName(String Name) {           //
        this.Name.set(Name);                     //
    }                                            //

    public String getSecond_name() {                    //
        return Second_name.get();                       //
    }                                                   //
                                                        //  геттер и сеттер для отчества продавца
    public void setSecond_name(String Second_name) {    //
        this.Second_name.set(Second_name);              //
    }                                                   //
}