package main.QuerySet;

import javax.sound.midi.Soundbank;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

public class Query_ContactList {

    private static Connection connection;

    //Tested

    /**
     *
     * @param updateContact
     * @throws SQLException
     */
    public static void AddNewcontacts( ContactList updateContact) throws SQLException {

        try{
            Connection connection = PostgresConnector.connectWithCredentials();

            if(connection != null){

                PreparedStatement insertContacts = connection.prepareStatement(
                        "INSERT INTO public.contact_list values(?,?,?,?,?)"
                );

                Random rand = new Random();

                insertContacts.setDouble(1, updateContact.getProd_id());
                insertContacts.setString(2, updateContact.getContact1_email());
                insertContacts.setString(3, updateContact.getContact2_email());
                insertContacts.setString(4, updateContact.getVendor_email());
                insertContacts.setInt(5, updateContact.getTrigger_level());

                insertContacts.executeUpdate();

                System.out.println("update commited");
            }
            else{
                System.out.println("Failed to connect: ");
            }
        } catch (Exception e){
            System.out.println("Connection failed: ");
            e.printStackTrace();
        }
    }

    public static void updateContact1(String newContact1, int prod_id) throws IOException, SQLException {
        try {
            Connection connection = PostgresConnector.connectWithCredentials();
            if (connection != null) {

                PreparedStatement updateQuery = connection.prepareStatement(
                        "UPDATE public.contact_list SET contact1_email = ? WHERE contact_list.prod_id=?");

                updateQuery.setString(1, newContact1);
                updateQuery.setInt(2, prod_id);
                updateQuery.executeUpdate();

                System.out.println("The item with product id: " + prod_id + "now has: " + newContact1+ " as a contact email");

            }

        } catch (SQLException e){
            throw new SQLException("Unable to connect", e);
        }
    }

    public static void updateContact2(String newContact2, int prod_id) throws IOException, SQLException {
        try {
            Connection connection = PostgresConnector.connectWithCredentials();
            if (connection != null) {

                PreparedStatement updateQuery = connection.prepareStatement(
                        "UPDATE public.contact_list SET contact2_email = ? WHERE contact_list.prod_id=?");

                updateQuery.setString(1, newContact2);
                updateQuery.setInt(2, prod_id);
                updateQuery.executeUpdate();

                System.out.println("The item with product id: " + prod_id + "now has: " + newContact2+ " as a contact email");

            }

        } catch (SQLException e){
            throw new SQLException("Unable to connect", e);
        }
    }

    public static void updateVendor(String newVendor, int prod_id) throws IOException, SQLException {
        try {
            Connection connection = PostgresConnector.connectWithCredentials();
            if (connection != null) {

                PreparedStatement updateQuery = connection.prepareStatement(
                        "UPDATE public.contact_list SET vendor_email = ? WHERE contact_list.prod_id=?");

                updateQuery.setString(1, newVendor);
                updateQuery.setInt(2, prod_id);
                updateQuery.executeUpdate();

                System.out.println("The item with product id: " + prod_id + "now has: " + newVendor+ " as a contact email");

            }

        } catch (SQLException e){
            throw new SQLException("Unable to connect", e);
        }
    }

    public static void updateTriggerLevel(int newTrigger, int prod_id) throws IOException, SQLException {
        try {
            Connection connection = PostgresConnector.connectWithCredentials();
            if (connection != null) {

                PreparedStatement updateQuery = connection.prepareStatement(
                        "UPDATE public.products_list SET vendor_email = ? WHERE contact_list.prod_id=?");

                updateQuery.setInt(1, newTrigger);
                updateQuery.setInt(2, prod_id);
                updateQuery.executeUpdate();

                System.out.println("The item with product id: " + prod_id + "now has: " + newTrigger+ " as it's email alert trigger level");

            }

        } catch (SQLException e){
            throw new SQLException("Unable to connect", e);
        }
    }

    public static void deleteContact(int prod_id) throws IOException, SQLException {
        try {
            Connection connection = PostgresConnector.connectWithCredentials();

            if (connection != null) {

                PreparedStatement updateQuery = connection.prepareStatement(
                        "DELETE FROM public.contact_list WHERE contact_list.prod_id=?");

                updateQuery.setInt(1, prod_id);
                updateQuery.executeUpdate();

                connection.close();

                System.out.println("The contacts product product id: " + prod_id + "has now been deleted");

            }


        } catch (SQLException e) {
            throw new SQLException("Unable to connect", e);
        }
    }

    public static ArrayList<Object> selectContactOnPID(int prod_id) throws IOException, SQLException {

        try {
            Connection connection = PostgresConnector.connectWithCredentials();

            if (connection != null) {

                PreparedStatement selectContacts = connection.prepareStatement(
                        "SELECT contact1_email, contact2_email from public.contact_list WHERE contact_list.prod_id= ?"
                );

                selectContacts.setInt(1, prod_id);
                ResultSet results = selectContacts.executeQuery();
                ArrayList<Object> returnedContacts = new ArrayList<>();

                while (results.next()) {
                    returnedContacts.add(results.getString(1));
                    returnedContacts.add(results.getString(2));
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

    public static ArrayList<Object> selectContact() throws IOException, SQLException {

        try {
            Connection connection = PostgresConnector.connectWithCredentials();

            if (connection != null) {

                PreparedStatement selectContacts = connection.prepareStatement(
                        "SELECT contact1_email, contact2_email from public.contact_list limit 1"
                );

               // selectContacts.setInt(1, prod_id);
                ResultSet results = selectContacts.executeQuery();
                ArrayList<Object> returnedContacts = new ArrayList<>();

                while (results.next()) {
                    returnedContacts.add(results.getString(1));
                    returnedContacts.add(results.getString(2));
                }
                connection.close();
                return returnedContacts;
            } else {
                throw new SQLException("unable to find: ");
            }
        }
        catch (SQLException e){
            throw new SQLException("Unable to connect", e);
        }

    }

    public static ArrayList<Object> selectVendorOnPID(int prod_id) throws IOException, SQLException {

        try {
            Connection connection = PostgresConnector.connectWithCredentials();

            if (connection != null) {

                PreparedStatement selectContacts = connection.prepareStatement(
                        "SELECT vendor_email from public.contact_list WHERE contact_list.prod_id= ?"
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

    public static ArrayList<Object> selectTriggerOnPID(int prod_id) throws IOException, SQLException {

        try {
            Connection connection = PostgresConnector.connectWithCredentials();

            if (connection != null) {

                PreparedStatement selectContacts = connection.prepareStatement(
                        "SELECT trigger_level from public.contact_list WHERE contact_list.prod_id= ?"
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

    // FIXME Tidy up
    public static ArrayList<Object> selectContactInfoOnPID(int prod_id) throws IOException, SQLException {

        try {
            Connection connection = PostgresConnector.connectWithCredentials();

            if (connection != null) {


                PreparedStatement selectProductName = connection.prepareStatement(
                        "SELECT productname from public.products_list WHERE products_list.prod_id= ?"
                );

                selectProductName.setInt(1, prod_id);
                ResultSet results2 = selectProductName.executeQuery();
                ArrayList<Object> returnedContacts = new ArrayList<>();
                while (results2.next()) {

                    returnedContacts.add(results2.getString(1)); // productname

                }

                PreparedStatement selectContacts = connection.prepareStatement(
                        "SELECT * from public.contact_list WHERE contact_list.prod_id= ?"
                );

                selectContacts.setInt(1, prod_id);
                ResultSet results = selectContacts.executeQuery();





                while (results.next()) {

                    returnedContacts.add(results.getString(2)); // C!
                    returnedContacts.add(results.getString(3));// C2
                    returnedContacts.add(results.getString(4));// Vendor
                    returnedContacts.add(results.getString(5));// Trigger

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


    public static void main(String[] args) throws SQLException, IOException {
//        ContactList cot = new ContactList(11981,"fromole8@gmail.com", "paul-oj@hotmail.co.uk","paul-oj@hotmail.co.uk", 3);
//        ContactList cot2 = new ContactList(18742,"fromole8@gmail.com", "paul-oj@hotmail.co.uk","paul-oj@hotmail.co.uk", 2);
//
//        AddNewcontacts(cot);
//        AddNewcontacts(cot2);
       // System.out.println(selectContactOnPID(11981));
       // System.out.println(selectVendorOnPID(11981));
  //      System.out.println(selectContactInfoOnPID(11981));

     //   updateContact1("info.productionline@gmail.com", 11981);
        System.out.println(selectContact());
    }
}
