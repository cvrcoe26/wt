import java.sql.*;

public class UpdatableScrollable {
    public static void main(String[] args) {
        try {
            // for mysql database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbname", "root", "cvr123");
            
            // in case of oracle databse use these 
            // Class.forName("oracle.jdbc.OracleDriver");
            // Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "cvr123");
            
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT id, name FROM employees");
            System.out.println("Original ResultSet:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }
            rs.beforeFirst();
            if (rs.next()) {
                rs.updateString("name", "UpdatedName");
                rs.updateRow();
            }
            rs.moveToInsertRow();
            rs.updateInt("id", 101);
            rs.updateString("name", "NewEmployee");
            rs.insertRow();
            System.out.println("\nUpdated ResultSet:");
            rs.beforeFirst();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
