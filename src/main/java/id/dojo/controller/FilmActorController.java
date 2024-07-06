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
            List<FilmActor> tr = FilmActor.listFilmActorById(filmActor.getFilm_id()).getData();
            List<Actor> dataActor = new ArrayList<>();
            for(FilmActor filmActor1 : tr){
                Actor actor = Actor.dataActorById(filmActor1.getActor_id()).getData();
                dataActor.add(actor);
                filmActor.setActor(dataActor);
            }
        }
        ctx.json(gson.toJson(list));
    };

    public static Handler listFilmActorAll2 = ctx -> {
        Res<List<FilmActor>> list = FilmActor.listFilmActorAll2();

        List<FilmActor> listFilmAll = list.getData();
        for(FilmActor filmActor : listFilmAll){
            List<FilmActor> tr = FilmActor.listFilmActorById(filmActor.getFilm_id()).getData();
            List<Actor> dataActor = new ArrayList<>();
            Film film = Film.getFilmById(filmActor.getFilm_id()).getData();
            filmActor.setFilm(film);
            for(FilmActor filmActor1 : tr){
                Actor actor = Actor.dataActorById(filmActor1.getActor_id()).getData();
                dataActor.add(actor);
            }
            filmActor.setActor(dataActor);
        }
        ctx.json(gson.toJson(list));
    };
}
