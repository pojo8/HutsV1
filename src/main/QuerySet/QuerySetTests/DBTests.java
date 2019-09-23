package main.QuerySet.QuerySetTests;

import main.QuerySet.PostgresConnector;
import main.QuerySet.PostgresConnector.*;

import org.junit.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class DBTests {
    
    @Test
    public void connectToDBWithCredentials() throws IOException, SQLException {

        Connection ingres = PostgresConnector.connectWithCredentials();

        assertFalse("The opened  connection is not null", ingres == null);
        
    }
    
    
}
