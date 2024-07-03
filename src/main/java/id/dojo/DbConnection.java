package id.dojo;

import org.sql2o.Sql2o;

public class DbConnection {
    public static Sql2o sql2o;
    static {
        System.out.println("koneksi jalan!");
        sql2o = new Sql2o("jdbc:postgresql://localhost:5432/dvdrental", "udin", "udin");
    }

    public static Sql2o getSql2o(){
        return sql2o;
    }
}
