package com.test.storetransactionsystem;

import java.sql.*;
import java.util.ArrayList;

//-----------------------------------------------------------------------------------

public class DBAdapter {
    static Connection con;

    static void init(){
        if (con == null) {
            try {
                String URL = "Здесь должна быть ссылка";     //
                String LOGIN = "Здесь должен быть логин";                                        // подключение к базе данных
                String PASSWORD = "Здесь должен быть пароль";                         //
                con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            } catch (Exception e){
                System.out.println(e);
            }
        }
    }

//---------------------------------------------------------------------------------------

    static void insertNewSeller(String Surname, String Name, String Second_name) throws SQLException {      //  ввести нового продавца в БД
        String sql = "INSERT INTO seller(Surname, Name, Second_name) VALUES('" + Surname + "', '" + Name + "', '" + Second_name + "');";
        Statement stmt = con.createStatement();
        stmt.execute(sql);
        stmt.close();
    }

    static ArrayList<Seller> selectSellers() throws SQLException {   //  возвращает все данные из таблицы продавцов (нужно для обновления данных таблицы)
        ArrayList<Seller> Sellers = new ArrayList<Seller>();
        String sql = "SELECT *  FROM seller;";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            int id = rs.getInt("id-seller");
            String Surname = rs.getString("Surname");
            String Name = rs.getString("Name");
            String Second_name = rs.getString("Second_name");
            Sellers.add(new Seller(id, Surname, Name, Second_name));
        }
        return Sellers;
    }

    static void updateSeller(Integer id, String Surname, String Name, String Second_name) throws SQLException {    // изменить запись в таблице продавцов
        String sql = "UPDATE seller SET `Surname`='"+ Surname +"', `Name`='"+ Name +"', `Second_name`='"+ Second_name +"' WHERE `id-seller`="+ id +";";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

    static void deleteSeller(Integer id) throws SQLException {          // удалить запись в таблице продавцов
        String sql = "DELETE FROM seller WHERE `id-seller`="+ id +";";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

//--------------------------------------------------------------------------------------------------------    

    static void insertTransaction(int id_product, int id_seller, String Date) throws SQLException {   // добавить транзакцию в таблицу
        String sql = "INSERT INTO transaction(`id-product`, `id-seller`, `Date`) VALUES("+ id_product +", "+ id_seller +", '"+ Date +"');";
        Statement stmt = con.createStatement();
        stmt.execute(sql);
        stmt.close();
    }

    static ArrayList<Transaction> selectTransactions() throws SQLException {        // выбрать все транзакции (нужно для обновления данных таблицы)
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		String sql = """ 
                    SELECT transaction.`id-transaction`, product.`Model`, seller.`Surname`, transaction.`Date`
					FROM transaction
					INNER JOIN seller ON transaction.`id-seller` = seller.`id-seller` 
                    INNER JOIN product ON transaction.`id-product` = product.`id-product`;
                    """;
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            int id = rs.getInt("id-transaction");
            String model = rs.getString("Model");
            String surname = rs.getString("Surname");
            String date = rs.getString("Date");
            transactions.add(new Transaction(id, model, surname, date));
        }
        return transactions;
    }

    static void deleteTransaction(Integer id) throws SQLException {                  // удалить транзакцию  из таблицы
        String sql = "DELETE FROM transaction WHERE `id-transaction`="+ id +";";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

//----------------------------------------------------------------------------------------------------------------

    static void insertNewProduct(String Model, int Year_of_production, String Country, int Price) throws SQLException {      //  ввести новый товар в БД
        String sql = "INSERT INTO product(Model, Year_of_production, Country, Price) VALUES('" + Model + "', " + Year_of_production + ", '" + Country + "'," + Price + ");";
        Statement stmt = con.createStatement();
        stmt.execute(sql);
        stmt.close();
    }

    static ArrayList<Product> selectProducts() throws SQLException {   //  возвращает все данные из таблицы товаров (нужно для обновления данных таблицы)
        ArrayList<Product> Sellers = new ArrayList<Product>();
        String sql = "SELECT *  FROM product;";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            int id = rs.getInt("id-product");
            String model = rs.getString("Model");
            int year = rs.getInt("Year_of_production");
            String country = rs.getString("Country");
            int price = rs.getInt("Price");
            Sellers.add(new Product(id, model, year, country, price));
        }
        return Sellers;
    }

    static void updateProduct(Integer id, String Model, int Year, String Country, int Price) throws SQLException {    // изменить запись в таблице товаров
        String sql = "UPDATE product SET `Model`='"+ Model +"', `Year_of_production`="+ Year +", `Country`='"+ Country + "', `Price`="+ Price + " WHERE `id-product`="+ id +";";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

    static void deleteProduct(Integer id) throws SQLException {          // удалить запись в таблице товаров
        String sql = "DELETE FROM product WHERE `id-product`="+ id +";";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }
}