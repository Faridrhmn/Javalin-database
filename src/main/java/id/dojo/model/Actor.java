package id.dojo.model;

import id.dojo.helper.Res;
import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.List;

import static id.dojo.DbConnection.sql2o;

public class Actor {
    private int actor_id;
    private String first_name;
    private String last_name;
    private Timestamp last_update;

    public String toString(){
        return "id = " + this.getActor_id() + " first name : " + this.getFirst_name() + " last name : " + this.getLast_name() + " last update : " + this.getTs() + "\n";
    }

    public int getActor_id() {
        return actor_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public Timestamp getTs() {
        return last_update;
    }

    public static Res<List<Actor>> dataActor(){
        try(Connection con = sql2o.open()){
            List<Actor> actors = con.createQuery("select actor_id,first_name,last_name,last_update from actor order by actor_id desc limit 10").executeAndFetch(Actor.class);
            System.out.println(actors);
            Res<List<Actor>> res = new Res<List<Actor>>("Berhasil ambil data aktor", actors);
            return res;
        }
    }

    public static Res<List<Actor>> dataActorById(String actor_id){
        try(Connection con = sql2o.open()){
            String sql = "SELECT actor_id, first_name, last_name, last_update FROM actor WHERE actor_id="+actor_id;
            List<Actor> actors = con.createQuery(sql)
                    .executeAndFetch(Actor.class);
            System.out.println(actors + actor_id);
            Res<List<Actor>> res = new Res<List<Actor>>("Berhasil ambil data aktor dengan id " + actor_id, actors);
            return res;
        }
    }

    public static Res<List<Actor>> makeActor(String first_name, String last_name){
        try(Connection con = sql2o.open()){
            String sql = "insert into actor (first_name, last_name) values (:first_name, :last_name)";
            con.createQuery(sql)
                    .addParameter("first_name", first_name)
                    .addParameter("last_name", last_name)
                    .executeUpdate();
            Res<List<Actor>> res = new Res<List<Actor>>("Berhasil input data actor", null);
            return res;
        }
    }

    public static Res<List<Actor>> changeActor(Integer actor_id, String first_name, String last_name){
        try(Connection con = sql2o.open()){
            String sql = "update actor set first_name=:first_name,last_name=:last_name where actor_id=:actor_id";
            con.createQuery(sql)
                    .addParameter("actor_id", actor_id)
                    .addParameter("first_name", first_name)
                    .addParameter("last_name", last_name)
                    .executeUpdate();
            Res<List<Actor>> res = new Res<List<Actor>>("Berhasil update data actor", null);
            return res;
        }
    }

    public static Res<List<Actor>> deleteActor(String actor_id){
        try(Connection con = sql2o.open()){
            String sql = "delete FROM actor WHERE actor_id="+actor_id;
            con.createQuery(sql)
                    .executeUpdate();
            Res<List<Actor>> res = new Res<List<Actor>>("Berhasil hapus data aktor dengan id " + actor_id,null);
            return res;
        }
    }
}
