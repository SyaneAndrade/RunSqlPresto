package br.com.lab.jdbc.presto.sql;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

public class JdbcPrestoRun {

    private static String driverName = "com.facebook.presto.jdbc.PrestoDriver";
    private static Connection con;
    public Statement stmt;

    public JdbcPrestoRun(String caminhoPresto) throws SQLException {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }
        con = DriverManager.getConnection(caminhoPresto);
        stmt = con.createStatement();
    }

    public  void executeQuery(String query) {

        ResultSet set = null;

        try {
            System.out.println(query);
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                System.out.println(res.getString(1));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}