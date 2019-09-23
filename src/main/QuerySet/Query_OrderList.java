package main.QuerySet;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import java.util.Random;


public class Query_OrderList {

    private static Connection connection;

    public static void AddNewOrder( OrderList updateValues) throws SQLException {

        try {

            Connection connection = PostgresConnector.connectWithCredentials();

            if (connection != null) {

                PreparedStatement insertOrder = connection.prepareStatement("INSERT INTO public.orders_list values(?,?,?,?,?,?)");

                Random rand = new Random();

                insertOrder.setDouble(1, updateValues.getOrder_id());
                insertOrder.setDate(2, Date.valueOf(updateValues.getDate_ordered()));
                insertOrder.setInt(3, updateValues.getProd_id());
                insertOrder.setInt(4, updateValues.getLot_size());
                insertOrder.setInt(5, updateValues.getRemaining_lot());
                insertOrder.setString(6, updateValues.getGeneric_Qr_block());

                insertOrder.executeUpdate();
                System.out.println("update commited");
            } else {
                System.out.println("Failed to connect");
            }


        } catch (Exception e) {
            System.out.println("connection failed: ");

            e.printStackTrace();

        }

    }


    public static ArrayList<Object> selectRowOnProd_id ( int prod_id) throws SQLException, IOException{
        try {

            Connection connection = PostgresConnector.connectWithCredentials();

            if (connection != null) {

                PreparedStatement selectQuery = connection.prepareStatement(
                        "SELECT * FROM public.orders_list WHERE orders_list.prod_id=?");

                selectQuery.setInt(1, prod_id);
                ResultSet results = selectQuery.executeQuery();
                ArrayList<Object> returnedOrder = new ArrayList<>();


                while (results.next()) {
                    returnedOrder.add(results.getInt(1));

                    returnedOrder.add(results.getString(2));
                    returnedOrder.add(results.getInt(3));
                    returnedOrder.add(results.getInt(4));
                    returnedOrder.add(results.getInt(5));
                    returnedOrder.add(results.getString(6));

                    // values.add(incoming);

                }
                connection.close();
              //  System.out.println("retrieved result for: "+prod_id);
                return returnedOrder;
            } else {

                throw new SQLException("Unable to find: " +prod_id);

            }

        } catch (SQLException e){
            throw new SQLException("Unable to connect", e);
        }
    }

    public static ArrayList<Object> selectCurrenteOrders () throws SQLException, IOException{
        try {

            Connection connection = PostgresConnector.connectWithCredentials();

            if (connection != null) {

                PreparedStatement selectQuery = connection.prepareStatement(
                        "SELECT * FROM public.orders_list WHERE orders_list.remaining_lot!=0");


                ResultSet results = selectQuery.executeQuery();
                ArrayList<Object> returnedOrder = new ArrayList<>();


                while (results.next()) {
                    returnedOrder.add(results.getInt(1));

                    returnedOrder.add(results.getString(2));
                    returnedOrder.add(results.getInt(3));
                    returnedOrder.add(results.getInt(4));
                    returnedOrder.add(results.getInt(5));
                    returnedOrder.add(results.getString(6));

                    // values.add(incoming);

                }
                connection.close();
                //  System.out.println("retrieved result for: "+prod_id);
                return returnedOrder;
            } else {

                throw new SQLException("Unable to find current inventory ");

            }

        } catch (SQLException e){
            throw new SQLException("Unable to connect", e);
        }
    }

    // Tested
    // TODO put in zero checks
    public static void decreaseCurrentLot(int prod_id) throws SQLException, IOException{
        try{

            Connection connection = PostgresConnector.connectWithCredentials();

            if (connection != null){

                PreparedStatement decreaseLot = connection.prepareStatement("UPDATE public.orders_list SET remaining_lot = remaining_lot - 1 where prod_id = ? ");

                decreaseLot.setInt(1, prod_id);

                decreaseLot.executeUpdate();

            }
        }
        catch (SQLException e){
            throw new SQLException("Unable to connect", e);
        }

    }

    // for use of the repeat scanning
    public static void decreaseCurrentLotBy(int prod_id, int amount) throws SQLException, IOException{
        try{

            Connection connection = PostgresConnector.connectWithCredentials();

            if (connection != null){

                PreparedStatement decreaseLot = connection.prepareStatement("UPDATE public.orders_list SET remaining_lot = remaining_lot - ? where prod_id = ? ");

                decreaseLot.setInt(1,amount);
                decreaseLot.setInt(2, prod_id);

                decreaseLot.executeUpdate();

            }
        }
        catch (SQLException e){
            throw new SQLException("Unable to connect", e);
        }

    }

    public static void increaseCurrentLot(int prod_id) throws SQLException, IOException{
        try{

            Connection connection = PostgresConnector.connectWithCredentials();

            if (connection != null){

                PreparedStatement decreaseLot = connection.prepareStatement("UPDATE public.orders_list SET remaining_lot = remaining_lot + 1 where prod_id = ? ");

                decreaseLot.setInt(1, prod_id);

                decreaseLot.executeUpdate();

            }
        }
        catch (SQLException e){
            throw new SQLException("Unable to connect", e);
        }

    }

    // for use of the repeat scanning
    public static void increaseCurrentLotBy(int prod_id, int amount) throws SQLException, IOException{
        try{

            Connection connection = PostgresConnector.connectWithCredentials();

            if (connection != null){

                PreparedStatement decreaseLot = connection.prepareStatement("UPDATE public.orders_list SET remaining_lot = remaining_lot + ? where prod_id = ? ");

                decreaseLot.setInt(1,amount);
                decreaseLot.setInt(2, prod_id);

                decreaseLot.executeUpdate();

            }
        }
        catch (SQLException e){
            throw new SQLException("Unable to connect", e);
        }

    }


    public static void deleteOrder(int prod_id) throws IOException, SQLException {
        try {
            Connection connection = PostgresConnector.connectWithCredentials();

            if (connection != null) {

                PreparedStatement updateQuery = connection.prepareStatement(
                        "DELETE FROM public.orders_list WHERE orders_list.prod_id=?");

                updateQuery.setInt(1, prod_id);
                updateQuery.executeUpdate();

                connection.close();

                System.out.println("The Order with product id: " + prod_id + "has now been deleted");

            }


        }
        catch (SQLException e){
            throw new SQLException("Unable to connect", e);
        }
    }

    //TODO Special select method for all non zero values
    // TODO an most recent ordereing of product
    public static void main(String [] args) throws Exception {

//        OrderList huh = new OrderList(0,"2018-1-05", 18742,12,14, "Moros clan");
//
//        AddNewOrder(huh);

        System.out.println(QueryUtilities.orderssSet(selectCurrenteOrders()));
    }
}
