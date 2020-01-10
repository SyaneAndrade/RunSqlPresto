package br.com.lab.jdbc;

import java.io.IOException;
import java.sql.*;
import br.com.lab.jdbc.io.FileIO;
import br.com.lab.jdbc.presto.sql.JdbcPrestoRun;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        String urlPrestoJdbc = "";

        try {
            urlPrestoJdbc = args[0];
        }
        catch(Exception e){
            System.out.println("Necessário para como primeiro parametro a url para conexão jdbc com o Presto;");
            System.exit(1);
        }
        String file = "";
        try {
            file = args[1];
        }
        catch (Exception e){
            System.out.println("Necessário passar como segundo parametro o arquivo com as querys que serão executadas;");
            System.exit(1);
        }
        FileIO qrio = new FileIO();

        JdbcPrestoRun jdbcprestorun = new JdbcPrestoRun(urlPrestoJdbc);

        FileIO qrio_presto = new FileIO();

        String conteudoFile = qrio_presto.lerArquivo(file);

        String[] querys = qrio_presto.criaArrayQuerys(conteudoFile);

        for (String query: querys){
            jdbcprestorun.executeQuery(query);
        }
    }
}
