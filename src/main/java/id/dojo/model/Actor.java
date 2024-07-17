package id.dojo.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import id.dojo.helper.Res;
import id.dojo.helper.DBUtils;
import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

import static id.dojo.DbConnection.sql2o;

public class Actor {
    private int actor_id;
    private String first_name;
    private String last_name;
    private Timestamp last_update;
    private Actor actor;
    static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public Actor() {
    }

    public Actor(int i, String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public String toString(){
        return "id = " + this.getActor_id() + " first name : " + this.getFirst_name() + " last name : " + this.getLast_name() + " last update : " + this.getTs() + "\n";
    }

    public Integer getActor_id() {
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

    public Actor getActor() {
        return actor;
    }

    public static Res<List<Actor>> listActor(Map<String,String> params, Integer page){
        Integer offset = 0;
        if(page != null || page > 0){
            offset = (page-1)*10;
        }
        String sql = "select actor_id,first_name,last_name,last_update from actor WHERE TRUE";
        String where = "";
        if(params.get("firstname") != null && params.get("firstname") != ""){
            where += " AND first_name ILIKE '%" + params.get("firstname") + "%'";
        }

        if(params.get("lastname") != null && params.get("lastname") != ""){
            where += " AND last_name ILIKE '%" + params.get("lastname") + "%'";
        }

        Res data = new DBUtils().list(
                sql + where + " ORDER BY actor_id LIMIT 10 OFFSET " + offset,
                Actor.class
        );
        return data;
    }

    public static Res<List<Actor>> dataActor(){
        Res<List<Actor>> res = new DBUtils<Actor>().list(
                "select actor_id,first_name,last_name,last_update from actor order by actor_id desc limit 10", Actor.class
        );
        return res;
    }

    public static Res<List<Actor>> dataFilmActor(Integer film_id){
        Res<List<Actor>> res = new DBUtils<Actor>().list(
                "SELECT ac.* FROM film_actor fa " +
                        "JOIN actor ac ON fa.actor_id = ac.actor_id " +
                        "WHERE fa.film_id=" + film_id, Actor.class
        );
        return res;
    }

    public static Res<Actor> dataActorById(Integer actor_id){
        Res<Actor> res = new DBUtils<Actor>().get("SELECT actor_id, first_name, last_name, last_update FROM actor WHERE actor_id= :p1;", actor_id, Actor.class);
        return res;
    }

    public static Res<List<Actor>> listActorById(Integer actor_id){
        Res<List<Actor>> res = new DBUtils<Actor>().list(
                "SELECT actor_id, first_name, last_name, last_update FROM actor WHERE actor_id="+actor_id, Actor.class
        );
        return res;
    }

    public static Res<Actor> makeActor(String first_name, String last_name){
        try(Connection con = sql2o.open()){
            String sql = "insert into actor (first_name, last_name) values (:first_name, :last_name)";
            con.createQuery(sql)
                    .addParameter("first_name", first_name)
                    .addParameter("last_name", last_name)
                    .executeUpdate();
//            con.createQuery(sql)
////                    .bind(actor)
//                    .executeUpdate();
            Res<Actor> res = new Res<Actor>("Berhasil input data actor", null);
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

    public static String postActor(Actor actor){
        Res data = new DBUtils().insert(
                "INSERT INTO actor (first_name, last_name)" +
                        "VALUES ( :first_name, :last_name )", actor
        );
        return gson.toJson(data);
    }
}
