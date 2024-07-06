package id.dojo.model;

import com.google.gson.Gson;
import id.dojo.DbConnection;
import id.dojo.helper.DBUtils;
import id.dojo.helper.Res;
import org.sql2o.Sql2o;

import java.util.List;

public class FilmCategory {
    private Integer film_id;
    private Integer category_id;
    private java.sql.Timestamp last_update;
    private Film film;

    static Sql2o sql2o = DbConnection.getSql2o();
    static Gson gson = new Gson();

    public Integer getFilm_id() {
        return film_id;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public static Res<List<FilmCategory>> listFilmCategory(){
        Res<List<FilmCategory>> res = new DBUtils<FilmCategory>().list(
                "SELECT fc.film_id, fc.category_id, fc.last_update FROM film_category fc JOIN film f ON fc.film_id = f.film_id LIMIT 5", FilmCategory.class
        );
        return res;
    }

}