package id.dojo.controller;

import com.google.gson.Gson;
import id.dojo.helper.Res;
import id.dojo.model.Film;
import id.dojo.model.FilmCategory;
import io.javalin.http.Handler;

import java.util.List;

public class FilmCategoryController {
    static Gson gson = new Gson();
    public static Handler listFilmCategory = ctx -> {
        Res<List<FilmCategory>> list = FilmCategory.listFilmCategory();

        List<FilmCategory> listFilmCategory = list.getData();
        for(FilmCategory filmCategory : listFilmCategory){
            Film film = Film.getFilmById(filmCategory.getFilm_id()).getData();
            filmCategory.setFilm(film);
        }
        ctx.json(gson.toJson(list));
    };


}