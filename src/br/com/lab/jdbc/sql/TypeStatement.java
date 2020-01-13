package br.com.lab.jdbc.sql;

public class TypeStatement {

    public String statement;
    public Boolean update;

    public TypeStatement(String query){
        String[] verifica = query.split(" ");
        update = (verifica[0].toUpperCase().contains("SELECT") || verifica[0].toUpperCase().contains("SHOW")) ?
                Boolean.FALSE : Boolean.TRUE;
        statement = query;
    }
}
