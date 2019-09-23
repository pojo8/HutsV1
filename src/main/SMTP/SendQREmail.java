package main.SMTP;

import main.QuerySet.Query_ContactList;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class SendQREmail {

    /**
     * connected ...
     * [fromole8@gmail.com, paul-oj@hotmail.co.uk]
     * connected ...
     * [paul-oj@hotmail.co.uk]
     * connected ...
     * [3]
     */

    //TODO atttach QRcode page to email

    private static String contact1Email;
    private static String contact2Email;
    private static String productName;
    private static String triggerLevel;
    private static ArrayList contactInfo;

    // basic test
    public static Message createQREmail(int prod_id){

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


            BodyPart messageBody = new MimeBodyPart();


            //Email text
            messageBody.setText("Dear Sir or Madam,\n\n" +
                    "This is sent with relative path. A test of basic file sent with attachment (heart) ");



            Multipart multipartEmail = new MimeMultipart();

            multipartEmail.addBodyPart(messageBody);

            //Part two is the attachment
            messageBody = new MimeBodyPart();

            // Fore the path relative to the project: include src/
            String filename = "src/main/resources/commonApp.properties";

            DataSource source = new FileDataSource(filename);
            messageBody.setDataHandler(new DataHandler(source));
            messageBody.setFileName("app.properties");
            multipartEmail.addBodyPart(messageBody);

            message.setContent(multipartEmail);

            System.out.println("email with attachment has been sent");

        } catch (MessagingException e) {
            e.printStackTrace();
            //TODO generate err msg
        }
        return message;
    }

    public static void main(String[] args) throws IOException {

        int prod_id = 11981;

        Message message = createQREmail(prod_id);

        Email_Utilities.transportEmail(message);

    }

}
