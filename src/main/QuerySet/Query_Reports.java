package main.QuerySet;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;


// A set of querys used in generating the reports

public class Query_Reports {


    private static Connection connection;

    public static ArrayList<Object> selectAllProductnames() throws SQLException, IOException {
        try {
            Connection connection = PostgresConnector.connectWithCredentials();

            if (connection != null) {

                PreparedStatement selectProdsQuery = connection.prepareStatement(
                        "SELECT productname FROM public.products_list"
                );

                ResultSet results = selectProdsQuery.executeQuery();
                ArrayList<Object> returnedProducts = new ArrayList<>();

                while (results.next()) {
                    returnedProducts.add(results.getString(1));
                }

                connection.close();

                return returnedProducts;


            }
            else {

                throw new SQLException("Unable to find current inventory ");

            }

        }
        catch (SQLException e) {
            throw new SQLException("Unable to connect", e);
        }
    }



        public static ArrayList<Object> selectAllProductPrices () throws SQLException, IOException {

            try {
                Connection connection = PostgresConnector.connectWithCredentials();

                if (connection != null) {
                    PreparedStatement selectProdsQuery = connection.prepareStatement(
                            "SELECT rup FROM public.products_list"
                    );

                    ResultSet results = selectProdsQuery.executeQuery();
                    ArrayList<Object> returnedPrices = new ArrayList<>();

                    while (results.next()) {
                        returnedPrices.add(results.getDouble(1));
                    }

                    connection.close();

                    return returnedPrices;

                }
                else {

                    throw new SQLException("Unable to find current inventory ");

                }

            }
            catch (SQLException e) {
                throw new SQLException("Unable to connect", e);
            }
        }

         public static ArrayList<Object> selectAllTotalOrders () throws SQLException, IOException {

           try {
                Connection connection = PostgresConnector.connectWithCredentials();

                if (connection != null) {

                    PreparedStatement selectTotalOrdersQuery = connection.prepareStatement(
                            "SELECT lot_size FROM public.orders_list"
                    );

                    ResultSet results = selectTotalOrdersQuery.executeQuery();
                    ArrayList<Object> returnedTotalOrders = new ArrayList<>();

                    while (results.next()) {
                        returnedTotalOrders.add(results.getInt(1));
                    }

                    connection.close();

                    return returnedTotalOrders;
                }

                else {

                    throw new SQLException("Unable to find current inventory ");

                }

           }
           catch (SQLException e) {
               throw new SQLException("Unable to connect", e);
           }
         }


                public static ArrayList<Object> selectAllRemainingOrders () throws SQLException, IOException {

                    try {
                        Connection connection = PostgresConnector.connectWithCredentials();

                        if (connection != null) {

                            PreparedStatement selectRemLotQuery = connection.prepareStatement(
                                    "SELECT remaining_lot FROM public.orders_list"
                            );

                            ResultSet results = selectRemLotQuery.executeQuery();
                            ArrayList<Object> returnedRemLot = new ArrayList<>();

                            while (results.next()) {
                               returnedRemLot.add(results.getString(1));
                           }

                            connection.close();

                           return returnedRemLot;
                        }

                        else {

                            throw new SQLException("Unable to find current inventory ");

                        }

                    }
                    catch (SQLException e) {
                        throw new SQLException("Unable to connect", e);
                    }
                }

    public static void main(String[] args) throws IOException, SQLException {

        System.out.println(selectAllProductnames());

        System.out.println(selectAllProductPrices());

        System.out.println(selectAllRemainingOrders());

        System.out.println(selectAllTotalOrders());


    }
}




