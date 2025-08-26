import java.sql.*;

public class DBConnect {
    public static void main(String[] args) {

        String connectionURL = "jdbc:sqlserver://10.140.130.17:1433;databaseName=Grp4;user=dba6;password=dba6;encrypt=true;trustServerCertificate=true;";


        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionURL);
            String SQL = "SELECT * FROM Buchung;";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            //Schleife für die Bearbeitung der DB
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }catch (Exception ex) {
            ex.printStackTrace();
        }

        finally {
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); }}
            if (stmt != null) { try { stmt.close(); } catch (Exception e) { e.printStackTrace(); }}
            if (con != null) { try { con.close(); } catch (Exception e) { e.printStackTrace(); }}
        }

    }
}
