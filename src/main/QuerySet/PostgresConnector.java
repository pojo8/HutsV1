package main.QuerySet;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresConnector {

    public static Connection connection;

    public static Connection connectWithCredentials() throws IOException, SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Cannot find postgres driver");
            e.printStackTrace();
        }

        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/commonApp.properties"));

        String url = properties.getProperty("dbURL");
        properties.setProperty("user", properties.getProperty("dbUser"));
        properties.setProperty("password", properties.getProperty("dbPassword"));
        properties.getProperty("ssl", properties.getProperty("dbSsl"));

        connection = DriverManager.getConnection(url, properties);
        return connection;
    }
}
