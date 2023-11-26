package lk.ijse.dep11;
import org.junit.jupiter.api.*;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class DatabaseTest {

    private Connection connection;

    @BeforeEach
    void setUp() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/filter_app","root","1995");
        
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.close();
    }

    @Order(1)
    @Test
    public void testDBExceeds1000records() throws SQLException {
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT COUNT(*) FROM customer");
        rst.next();
        int numberOfRecordds = rst.getInt(1);
        assertTrue(numberOfRecordds >= 1000);
    }
    @Order(2)
    @Test
     public void testValidContacts() throws SQLException {
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT *FROM customer WHERE contact NOT REGEXP '\\d{3}-\\d{7}'");
        assertFalse(rst.next());




    }
    @Order(3)
    @Test
    public void testUniqueContacts() throws SQLException {
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT contact, COUNT(contact) AS count FROM customer GROUP BY contact HAVING count>1");
        assertFalse(rst.next());



    }
    @Order(4)
    @Test
    public void testUniqueCustomerNames() throws SQLException {
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT CONCAT(first_name,' ',last_name) AS name,COUNT(*) AS count FROM customer GROUP BY first_name,last_name HAVING count>1");
        assertFalse(rst.next());



    }
}
