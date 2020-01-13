package br.com.lab.jdbc.presto.sql;

import br.com.lab.jdbc.sql.TypeStatement;

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

    public  void executeQuery(TypeStatement tipoQuery) {

        ResultSet res = null;

        try {
            System.out.println(tipoQuery.statement);
            if(tipoQuery.update) {
                System.out.print("Linhas processadas: ");
                System.out.println(stmt.executeUpdate(tipoQuery.statement));
            }
            else {
                res = stmt.executeQuery(tipoQuery.statement);
            while (res.next()) {
                System.out.println(res.getString(1));
            }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}