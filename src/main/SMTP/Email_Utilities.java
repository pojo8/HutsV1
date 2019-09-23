package main.SMTP;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.util.Properties;

public class Email_Utilities {

    //TODO move the static string varaibles the properites file and pull from there
    //TODO write the catch erro messages for specific error

    private static String emailUser = "info.productionline@gmail.com";
    private static String emailPassword = "hammer322";
    private static String host = "smtp.gmail.com";
    private  static String to ="paul-oj@hotmail.co.uk";     // retrieved from db
    private static String gmailPort ="465";


    public static Properties setEmailProperties() throws IOException {

        Properties properties = System.getProperties();

        properties.load(new FileInputStream("src/main/resources/commonApp.properties"));


        properties.put("mail.smtp.host",properties.getProperty("EmailHost"));
        properties.put("mail.smtp.port",properties.getProperty("EmailPort"));
        properties.put("mail.smtp.starttls.enable", properties.getProperty("StartTLS.enabled"));
        properties.put("mail.smtp.socketFactory.port", properties.getProperty("EmailPort"));
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");

        return properties;
    }

    //FIXME TIDY up
    public static Session createEmailSession(Properties emailProperties){
        Properties transport = new Properties();
        try {
            transport.load(new FileInputStream("src/main/resources/commonApp.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String email2 = transport.getProperty("SenderEmailAccount");
        String pass2 = transport.getProperty("SenderEmailPassword");
        Session emailSesion = Session.getInstance(emailProperties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email2, pass2);
                    }
                });

        return emailSesion;
    }

    //inital testing
    public static void transportEmail(Message email) throws IOException {

        Properties transport = new Properties();
        transport.load(new FileInputStream("src/main/resources/commonApp.properties"));


        try {
            Transport.send(email,transport.getProperty("SenderEmailAccount"),transport.getProperty("SenderEmailPassword"));
        } catch (MessagingException e1) {
            e1.printStackTrace();
        }
        System.out.println("done");
        }




    public static void main(String[] args) throws IOException {

//        Properties properties = System.getProperties();
//
//
//
//       // properties.put("mail.smtp.user",emailUser);
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.port","465");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.socketFactory.port", "465");
//        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        properties.put("mail.smtp.socketFactory.fallback", "false");
//        Session session = Session.getInstance(properties,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(emailUser, emailPassword);
//                    }
//                });

            Properties pops = setEmailProperties();

            Properties transportProps = new Properties();
            transportProps.load(new FileInputStream("src/main/resources/commonApp.properties"));

            Session session = createEmailSession(pops);

        try{


            Message msg = new MimeMessage(session);
            msg.setFrom( new InternetAddress(emailUser, "info@productionline"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to, "My guy"));
            msg.setSubject("R4 Testing -testing");
            msg.setText("sup my dude");

            transportEmail(msg);





//            Transport.send(msg,emailUser,emailPassword);
//            System.out.println("done");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}

