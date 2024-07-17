package id.dojo.controller;

import com.google.gson.Gson;
import id.dojo.helper.Res;
import id.dojo.model.Actor;
import id.dojo.model.Film;
import id.dojo.model.FilmActor;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.List;

public class FilmActorController {

    static Gson gson = new Gson();

    public static Handler listFilmActorAll = ctx -> {
        Res<List<FilmActor>> list = FilmActor.listFilmActorAll();

        List<FilmActor> listFilmAll = list.getData();
        for(FilmActor filmActor : listFilmAll){
            List<Actor> hehe = Actor.dataFilmActor(filmActor.getFilm_id()).getData();
            filmActor.setActor(hehe);
        }
        ctx.json(gson.toJson(list));
    };

    public static Handler listFilmActorAll2 = ctx -> {
        Res<List<FilmActor>> list = FilmActor.listFilmActorAll2();

        List<FilmActor> listFilmAll = list.getData();
        for(FilmActor filmActor : listFilmAll){
            List<Actor> hehe = Actor.dataFilmActor(filmActor.getFilm_id()).getData();
            filmActor.setActor(hehe);
            Film film = Film.getFilmById(filmActor.getFilm_id()).getData();
            filmActor.setFilm(film);
        }
        ctx.json(gson.toJson(list));
    };
}
