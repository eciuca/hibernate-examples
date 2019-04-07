package sql;


import java.sql.*;

public class JDBC {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/scoala?serverTimezone=UTC";
        String user = "root";
        String pass = "password";

        try (Connection conn = DriverManager.getConnection(url, user, pass);

             Statement stmt = conn.createStatement();

             ResultSet rs = stmt.executeQuery("select * from clasa"))

        {
            while (rs.next()) {
                int id = rs.getInt("idClasa");
                String numeClasa = rs.getString("numeClasa");
                System.out.println(id + "." + " " + numeClasa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
