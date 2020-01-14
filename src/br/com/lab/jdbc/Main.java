package br.com.lab.jdbc;

import java.io.IOException;
import java.sql.*;
import java.util.List;

import br.com.lab.jdbc.io.FileIO;
import br.com.lab.jdbc.presto.sql.JdbcPrestoRun;
import br.com.lab.jdbc.sql.TypeStatement;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        // URL passada como paramêtro
        String urlPrestoJdbc = "";
        try {
            urlPrestoJdbc = args[0];
        }
        catch(Exception e){
            System.out.println("Necessário para como primeiro parametro a url para conexão jdbc com o Presto;");
            System.exit(1);
        }
        // File passado como paramêtro
        String file = "";
        try {
            file = args[1];
        }
        catch (Exception e){
            System.out.println("Necessário passar como segundo parametro o arquivo com as querys que serão executadas;");
            System.exit(1);
        }

        //Inicializando conexão com o Presto
        JdbcPrestoRun jdbcprestorun = new JdbcPrestoRun(urlPrestoJdbc);

        //Criando parse do arquivo que estão com as querys
        FileIO qrio_presto = new FileIO();

        String contentFile = qrio_presto.readFile(file);

        List<String> querys = qrio_presto.makeArrayQuerys(contentFile);

        // Execução das querys
        for (String query: querys){
            TypeStatement tipoQuery = new TypeStatement(query);
            jdbcprestorun.execute(tipoQuery);
        }
    }
}
