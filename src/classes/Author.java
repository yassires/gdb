package classes;

import java.sql.*;

public class Author {
    private int id;
    private String name;


    public Author() {

    }

    public  Author(String name){
        this.name = name;
    }


    public int getId() {

        return id;
    }

    public String getName() {

        return name;
    }



}
