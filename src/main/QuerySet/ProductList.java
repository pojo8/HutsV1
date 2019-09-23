package main.QuerySet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

/**
 * This class contains the constructor along with getters and setters
 * for the information submitted to the db
 * To access the product List database
 */
public class ProductList {

    private static Connection connection;

    private static String productName;
    private static String initialOrderDate;
    private static String currentOrderDate;
    private static double price;
    private static String currency;
    private static int prod_id;


//    public  int getProductID(){
//        return ProductList_params[0];
//    }

    public ProductList(int prod_id, String productName, String initialOrderDate, String currentOrderDate, double price,
                       String currency) {
        this.prod_id = prod_id;
        this.productName = productName;
        this.initialOrderDate = initialOrderDate;
        this.currentOrderDate = currentOrderDate;
        this.price = price;
        this.currency = currency;
    }

    public String getProductName() {
        return productName;
    }

//    public String static getInitialOrderDate() {
//        return initialOrderDate;
//    }
//
//    public String getCurrentOrderDate() {
//        return currentOrderDate;
//    }

    public double getPrice() {
        return price;
    }

    public int getProd_id() {
        return prod_id;
    }

    public static void setProd_id(int prod_id) {
        ProductList.prod_id = prod_id;
    }

    public static void setProductName(String productName) {
        ProductList.productName = productName;
    }

    public static void setInitialOrderDate(String initialOrderDate) {
        ProductList.initialOrderDate = initialOrderDate;
    }

    public static void setCurrentOrderDate(String currentOrderDate) {
        ProductList.currentOrderDate = currentOrderDate;
    }

    public static void setPrice(double price) {
        ProductList.price = price;
    }

    public static void setCurrency(String currency) {
        ProductList.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public static String getInitialOrderDate(){
        return initialOrderDate;
    }

    public static String getCurrentOrderDate(){
        return currentOrderDate;
    }

}
