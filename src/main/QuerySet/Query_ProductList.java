package main.QuerySet;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

//import main.QuerySet.PostgresConnector;
/**
 * Contains the initial set of sql queries for the table product list
 */
public class Query_ProductList {
//     Todo see if the connection variable is needed in each class
//    private static Connection connection;

    private static Connection connection;



    public static void AddNewProduct(ProductList updateValues) throws SQLException {

        try {
            Connection connection = PostgresConnector.connectWithCredentials();
            if (connection != null) {

                PreparedStatement insertProducts = connection.prepareStatement(
                        "INSERT INTO public.products_list values(?,?,?,?,?,?)"
                );



                insertProducts.setDouble(1, updateValues.getProd_id());
                insertProducts.setString(2, updateValues.getProductName());
                insertProducts.setDate(3, Date.valueOf(updateValues.getInitialOrderDate()));
                insertProducts.setDate(4, Date.valueOf(updateValues.getCurrentOrderDate()));
                insertProducts.setDouble(5, updateValues.getPrice());
                insertProducts.setString(6, updateValues.getCurrency());

                insertProducts.executeUpdate();
                System.out.println("update commited");
            } else {
                System.out.println("Failed to connect");
            }


        } catch (Exception e) {
            System.out.println("connection failed: ");

            e.printStackTrace();

        }

    }

    public static ArrayList<Object> selectRowOnProductName(String productName) throws SQLException, IOException {

        try {
            Connection connection = PostgresConnector.connectWithCredentials();

            if (connection != null) {



                PreparedStatement selectQuery = connection.prepareStatement(
                        "SELECT * FROM public.products_list WHERE products_list.productname=?");

                selectQuery.setString(1, productName);
                ResultSet results = selectQuery.executeQuery();
                ArrayList<Object> returnedProducts = new ArrayList<>();


                while (results.next()) {
                    returnedProducts.add(results.getInt(1));

                    returnedProducts.add(results.getString(2));
                    returnedProducts.add(results.getString(3));
                    returnedProducts.add(results.getString(4));
                    returnedProducts.add(results.getDouble(5));
                    returnedProducts.add(results.getString(6));


                }
                connection.close();
                return returnedProducts;
            } else {


                throw new SQLException("Unable to find: " + productName);

            }

        } catch (SQLException e){
            throw new SQLException("Unable to connect", e);
        }
    }


    // TODO continue refactor
    public static ArrayList<Object> selectRowOnProduct_id(int prod_id) throws SQLException, IOException {

        try {

            Connection connection = PostgresConnector.connectWithCredentials();
            if (connection != null) {

                PreparedStatement selectQuery = connection.prepareStatement(
                        "SELECT * FROM public.products_list WHERE products_list.prod_id=?");


                selectQuery.setInt(1, prod_id);
                ResultSet results = selectQuery.executeQuery();
                ArrayList<Object> returnedProducts = new ArrayList<>();

                while (results.next()) {
                    returnedProducts.add(results.getInt(1));

                    returnedProducts.add(results.getString(2));
                    returnedProducts.add(results.getString(3));
                    returnedProducts.add(results.getString(4));
                    returnedProducts.add(results.getDouble(5));
                    returnedProducts.add(results.getString(6));


                }
                return returnedProducts;
            } else {


                throw new SQLException("Unable to find: " + prod_id);

            }
        }catch (SQLException e){
            throw new SQLException("Unable to connect", e);
        }


    }

    // update queries act on prod_id
    public static void updateProductName(String newProductName, int prod_id) throws IOException, SQLException {


        try {
            connection = PostgresConnector.connectWithCredentials();
            if (connection != null) {

                PreparedStatement updateQuery = connection.prepareStatement(
                        "UPDATE public.products_list SET productname= ? WHERE products_list.prod_id=?");

                updateQuery.setString(1, newProductName);
                updateQuery.setInt(2, prod_id);
                updateQuery.executeUpdate();

                System.out.println("The item with product id: " + prod_id + "now has the name" + newProductName);

            }

        } catch (SQLException e){
            throw new SQLException("Unable to connect", e);
        }
    }


    // FIXME
    public static ArrayList<Object> selectProductOnPID(int prod_id) throws IOException, SQLException {

        try {
            Connection connection = PostgresConnector.connectWithCredentials();

            if (connection != null) {

                PreparedStatement selectContacts = connection.prepareStatement(
                        "SELECT trigger_level from public.products_list WHERE products_list.prod_id= ?"
                );

                selectContacts.setInt(1, prod_id);
                ResultSet results = selectContacts.executeQuery();
                ArrayList<Object> returnedContacts = new ArrayList<>();

                while (results.next()) {
                    returnedContacts.add(results.getString(1));

                }
                connection.close();
                return returnedContacts;
            } else {
                throw new SQLException("unable to find: " + prod_id);
            }
        }
        catch (SQLException e){
            throw new SQLException("Unable to connect", e);
        }

    }


    public static ArrayList<Object> selectProductAnd2Lot()throws IOException, SQLException {

        try {
            Connection connection = PostgresConnector.connectWithCredentials();

            if (connection != null) {

                PreparedStatement selectProducts = connection.prepareStatement(
                        "SELECT products_list.productName, orders_list.remaining_lot  " +
                                "from public.products_list JOIN orders_list ON products_list.prod_id = orders_list.prod_id"
                );

                ResultSet results = selectProducts.executeQuery();
                ArrayList<Object> returnedProducts = new ArrayList<>();

                while (results.next()) {
                    returnedProducts.add(results.getString(1));
                    returnedProducts.add(results.getString(2));


                }
                connection.close();
                return returnedProducts;
            } else {
                throw new SQLException("No products found");
            }
        }
        catch (SQLException e){
            throw new SQLException("Unable to connect", e);
        }

    }

    public static ArrayList<Object> selectProductAndLot()throws IOException, SQLException {

        try {
            Connection connection = PostgresConnector.connectWithCredentials();

            if (connection != null) {

                PreparedStatement selectProducts = connection.prepareStatement(
                        "SELECT products_list.productName, orders_list.remaining_lot, products_list.prod_id  " +
                                "from public.products_list JOIN orders_list ON products_list.prod_id = orders_list.prod_id"
                );

                ResultSet results = selectProducts.executeQuery();
                ArrayList<Object> returnedProducts = new ArrayList<>();

                while (results.next()) {
                    returnedProducts.add(results.getString(1));
                    returnedProducts.add(results.getString(2));
                    returnedProducts.add(results.getString(3));


                }
                connection.close();
                return returnedProducts;
            } else {
                throw new SQLException("No products found");
            }
        }
        catch (SQLException e){
            throw new SQLException("Unable to connect", e);
        }

    }

    public static void updateRUP(double newRUP, int prod_id) throws IOException, SQLException {

        try {
            Connection connection = PostgresConnector.connectWithCredentials();
            if (connection != null) {

                PreparedStatement updateQuery = connection.prepareStatement(
                        "UPDATE public.products_list SET RUP= ? WHERE products_list.prod_id=?");

                updateQuery.setDouble(1, newRUP);
                updateQuery.setInt(2, prod_id);
                updateQuery.executeUpdate();

                System.out.println("The item with product id: " + prod_id + "now has the Rough unit price: " + newRUP);

                connection.close();

            }

        } catch (SQLException e){
            throw new SQLException("Unable to connect", e);
        }
    }

    public static void updateCurrency(String newCurrency, int prod_id) throws IOException, SQLException {
        try {
            Connection connection = PostgresConnector.connectWithCredentials();
            if (connection != null) {

                PreparedStatement updateQuery = connection.prepareStatement(
                        "UPDATE public.products_list SET currency= ? WHERE products_list.prod_id=?");

                updateQuery.setString(1, newCurrency);
                updateQuery.setInt(2, prod_id);
                updateQuery.executeUpdate();

                System.out.println("The item with product id: " + prod_id + "now has the Currency: " + newCurrency);

            }

        } catch (SQLException e){
            throw new SQLException("Unable to connect", e);
        }
    }


    public static void deleteProduct(int prod_id) throws IOException, SQLException {
        try {
            Connection connection = PostgresConnector.connectWithCredentials();

            if (connection != null) {

                PreparedStatement updateQuery = connection.prepareStatement(
                        "DELETE FROM public.products_list WHERE products_list.prod_id=?");

                updateQuery.setInt(1, prod_id);
                updateQuery.executeUpdate();

                connection.close();

                System.out.println("The item with product id: " + prod_id + "has now been deleted");

            }


        }
        catch (SQLException e){
            throw new SQLException("Unable to connect", e);
        }
    }

    public static void main(String[] args) throws IOException, SQLException {


//        ProductList newProdList = new ProductList(111,"hasmmer", "2018-09-09"
//                , "2018-09-09", 12.00, "GBP");
//
//
//        AddNewProduct(newProdList);
      //  System.out.println(selectRowOnProductName("Mobile Armour"));
        System.out.println(selectProductAndLot());
    }
}


