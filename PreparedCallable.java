import java.sql.*;

public class PreparedCallable {
    public static void main(String[] args) {
        try {
            // for mysql database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbname", "root", "cvr123");
            
            // in case of oracle databse use these 
            // Class.forName("oracle.jdbc.OracleDriver");
            // Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "cvr123");
            
            String insertSQL = "INSERT INTO employees (id,name) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
            pstmt.setInt(1, 1000);
            pstmt.setString(2, "John");
            pstmt.executeUpdate();
            CallableStatement cstmt = conn.prepareCall("{CALL getEmployeeCount(?)}");
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.execute();
            int count = cstmt.getInt(1);
            System.out.println("Employee Count: " + count);
            String selectSQL = "SELECT id, name FROM employees";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectSQL);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.print("ID: " + id);
                System.out.print(", Name: " + name);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
