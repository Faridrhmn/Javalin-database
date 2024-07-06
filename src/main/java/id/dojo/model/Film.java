package id.dojo.model;

import id.dojo.helper.DBUtils;
import id.dojo.helper.Res;

public class Film {
    private Integer film_id;
    private String title;
    private String description;
    private Film film;

    public Integer getFilm_id() {
        return film_id;
    }

    public Film getFilm() {
        return film;
    }

    public static Res<Film>getFilmById(Integer filmId){
        Res<Film> data = new DBUtils<Film>().get("SELECT film_id, title, description FROM film WHERE film_id = :p1;", filmId, Film.class);
        return data;
    }
}