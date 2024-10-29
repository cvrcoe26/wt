import java.sql.*;

public class SimpleQueries {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbname", "root", "cvr123");
            Statement stmt = conn.createStatement();
            String sql = "SELECT id, name FROM employees";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.print("ID: " + id);
                System.out.print(", Name: " + name);
                System.err.println();
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
