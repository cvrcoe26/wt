import java.sql.*;

public class Metadata {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbname", "root", "cvr123");
            DatabaseMetaData dbMetaData = conn.getMetaData();
            System.out.println("Database Product Name: " + dbMetaData.getDatabaseProductName());
            System.out.println("Database Product Version: " + dbMetaData.getDatabaseProductVersion());
            System.out.println("JDBC Driver Name: " + dbMetaData.getDriverName());
            System.out.println("JDBC Driver Version: " + dbMetaData.getDriverVersion());
            ResultSet tables = dbMetaData.getTables(null, null, "%", new String[] { "TABLE" });
            System.out.println("\nTables in the database:");
            while (tables.next()) {
                System.out.println(tables.getString("TABLE_NAME"));
            }
            System.out.println("\nColumns in the employees table:");
            ResultSet columns = dbMetaData.getColumns(null, null, "employees", "%");
            while (columns.next()) {
                System.out.println("Column Name: " + columns.getString("COLUMN_NAME") +
                        ", Data Type: " + columns.getString("TYPE_NAME") +
                        ", Column Size: " + columns.getInt("COLUMN_SIZE"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
