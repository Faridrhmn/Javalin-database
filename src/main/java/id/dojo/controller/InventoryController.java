package id.dojo.controller;

import com.google.gson.Gson;
import id.dojo.helper.Res;
import id.dojo.model.Film;
import id.dojo.model.FilmCategory;
import id.dojo.model.Inventory;
import io.javalin.http.Handler;

import java.util.List;

public class InventoryController {

    static Gson gson = new Gson();
    public static Handler listInventoryFilm = ctx -> {
        Res<List<Inventory>> list = Inventory.listInventoryFilm();

        List<Inventory> listInventoryFilm = list.getData();
        for(Inventory inventory : listInventoryFilm){
            Film film = Film.getFilmById(inventory.getFilm_id()).getData();
            inventory.setFilm(film);
        }
        ctx.json(gson.toJson(list));
    };

}
