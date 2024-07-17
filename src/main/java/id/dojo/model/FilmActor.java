package id.dojo.model;

import com.google.gson.Gson;
import id.dojo.DbConnection;
import id.dojo.helper.DBUtils;
import id.dojo.helper.Res;
import org.sql2o.Sql2o;

import java.sql.Timestamp;
import java.util.List;

public class FilmActor {
    private Integer actor_id;
    private Integer film_id;
    private Timestamp last_update;
    private Integer actor_in_film;
    private Actor actor2;
    private List<Actor> actor;
    private Integer actor_ids;
    private List<FilmActor> filmActor;
    private Film film;

    static Sql2o sql2o = DbConnection.getSql2o();
    static Gson gson = new Gson();

    public Integer getFilm_id() {
        return film_id;
    }

    public Integer getActor_id() {
        return actor_id;
    }

    public void setActor(Actor actor2) {
        this.actor2 = actor2;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public void setActor(List<Actor> actor) {
        this.actor = actor;
    }

    public void setFilmActor(List<FilmActor> filmActor) {
        this.filmActor = filmActor;
    }

    public static Res<List<FilmActor>> listFilmActor(){
        Res<List<FilmActor>> res = new DBUtils<FilmActor>().list(
                "SELECT fa.actor_id, fa.film_id, fa.last_update FROM film_actor fa JOIN actor ac ON fa.actor_id = ac.actor_id LIMIT 5", FilmActor.class
        );
        return res;
    }

    public static Res<List<FilmActor>> listFilmActorAll(){
        Res<List<FilmActor>> res = new DBUtils<FilmActor>().list(
                "SELECT fa.film_id, COUNT(fa.film_id) AS actor_in_film FROM film_actor fa JOIN actor ac ON fa.actor_id = ac.actor_id GROUP BY fa.film_id LIMIT 5", FilmActor.class
        );
        return res;
    }

    public static Res<List<FilmActor>> listFilmActorAll2(){
        Res<List<FilmActor>> res = new DBUtils<FilmActor>().list(
                "SELECT fa.film_id, COUNT(fa.film_id) AS actor_in_film FROM film_actor fa JOIN actor ac ON fa.actor_id = ac.actor_id JOIN film fi ON fa.film_id = fi.film_id GROUP BY fa.film_id LIMIT 5", FilmActor.class
        );
        return res;
    }

    public static Res<List<FilmActor>> listFilmActorById(Integer film_id){
        Res<List<FilmActor>> res = new DBUtils<FilmActor>().list(
                "SELECT actor_id FROM film_actor where film_id="+film_id, FilmActor.class
        );
        return res;
    }

}
