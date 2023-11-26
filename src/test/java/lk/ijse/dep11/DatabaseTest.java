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
     public void testValidContacts(){
        System.out.println("2");

    }
    @Order(3)
    @Test
    public void testUniqueContacts(){
        System.out.println("3");

    }
    @Order(4)
    @Test
    public void testUniqueCustomerNames(){
        System.out.println("4");

    }
}
