package reminder;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Alen Joseph 14/10/2018
 */
public class dbcon {

    /**
     * This class is used create database connection
     */

    Connection con = null;

    public static java.sql.Connection dbConnector() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/remainderdb", "root", "");
            System.out.println("Connection is Successfull");
            return con;

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
            return null;
        }
    }
}
