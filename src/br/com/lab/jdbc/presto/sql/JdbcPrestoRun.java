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

    public JdbcPrestoRun(String urlPresto) throws SQLException {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }
        con = DriverManager.getConnection(urlPresto);
        stmt = con.createStatement();
    }

    public  void execute(TypeStatement typeQuery) {

        ResultSet res = null;

        try {
            System.out.println(typeQuery.statement);
            if(typeQuery.update) {
                System.out.print("Linhas processadas: ");
                System.out.println(stmt.executeUpdate(typeQuery.statement));
            }
            else if(typeQuery.select) {
                res = stmt.executeQuery(typeQuery.statement);
                System.out.println(res.getMetaData().getColumnCount());
                while (res.next()) {
                    for(int i=1; i < res.getMetaData().getColumnCount(); i++){
                        System.out.print(res.getString(i) + " ");
                    }
                    System.out.println(" ");
                }
            }
            else{
                res = stmt.executeQuery(typeQuery.statement);
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