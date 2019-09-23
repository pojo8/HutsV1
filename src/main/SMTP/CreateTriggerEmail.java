package main.SMTP;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import main.QuerySet.Query_ContactList;
import main.QuerySet.Query_ContactList.*;


public class CreateTriggerEmail {
    /**
     * connected ...
     * [fromole8@gmail.com, paul-oj@hotmail.co.uk]
     * connected ...
     * [paul-oj@hotmail.co.uk]
     * connected ...
     * [3]
     */

    //TODO pull the information from db add to email

    private static String contact1Email;
    private static String contact2Email;
    private static String productName;
    private static String triggerLevel;
    private static ArrayList contactInfo;
    //TODO generate sql request to get Contacts prod and tlevel (most recent order)





    //FIXME Wrap the session provider in the message creation
    public static Message createContactEmail(int prod_id){

        Properties pops = null;
        try {
            pops = Email_Utilities.setEmailProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Session emailSession = Email_Utilities.createEmailSession(pops);

        try {
            contactInfo = Query_ContactList.selectContactInfoOnPID(prod_id);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       // contact1Email = "paul-oj@hotmail.co.uk";
        String productName= contactInfo.get(0).toString();
        String contact1Email= contactInfo.get(1).toString();
        String contact2Email= contactInfo.get(2).toString();
        String vendorEmail= contactInfo.get(3).toString();
        String triggerLevel= contactInfo.get(4).toString();
//        System.out.println(productName);

        Properties transportProps = new Properties();
        try {
            transportProps.load(new FileInputStream("src/main/resources/commonApp.properties"));
        } catch (IOException e) {

            e.printStackTrace();
            //TODO generate err msg
        }

        Message message = new MimeMessage(emailSession);

        try {
            message.setFrom( new InternetAddress( transportProps.getProperty("SenderEmailAccount")));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                    contact1Email
            ));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                     contact2Email
            ));

            message.setSubject("Product: "+ productName + " is running low");

            message.setText("Dear Sir or Madam,\n\n" +
                    "The product:  "+ productName + " has just reached the desired trigger level of: " +triggerLevel+" . It would be beneficial to place a repeat order soon.\n" +
                    "You can notify your current product vendor via the application to begin preparing the next order.\n\n" +
                    "Kind regards,\n\n" +
                    "the Product Line team ");


        } catch (MessagingException e) {
            e.printStackTrace();
            //TODO generate err msg
        }
        return message;
    }


    public static void main(String[] args) throws IOException, MessagingException, SQLException {

        int prod_id = 11981;


        Message message= createContactEmail(prod_id);


            Email_Utilities.transportEmail(message);

    }

}
