package id.dojo;
import com.google.gson.Gson;
import id.dojo.controller.*;

import io.javalin.Javalin;
import static id.dojo.model.Payment.dataPayment;

public class Main {
    static Gson gson = new Gson();
    public static void main(String[] args) {

        var app = Javalin.create()
//                ACTOR
                .get("/actors", ActorController.listActorApi)
                .get("/actor/<actor_id>", ActorController.getActorIdApi)
                .get("/actors/search/<page>", ActorController.getListActorApinya)
                .post("/actor", ActorController.postActorApi)
                .post("/actor/bind", ActorController.postActorBind)
                .put("/actor", ActorController.updateActorApi)
                .delete("/actor/<actor_id>", ActorController.deleteActorApi)
//                FILM
                .get("/film-category", FilmCategoryController.listFilmCategory)
                .get("/film-inventory", InventoryController.listInventoryFilm)
                .get("/film-actor-all", FilmActorController.listFilmActorAll)
                .get("/film-actor-all2", FilmActorController.listFilmActorAll2)
//                PAYMENT
                .get("/payments", ctx -> ctx.json(gson.toJson(dataPayment())))
//                CITY
                .get("/city", CityController.listDataCity)
                .get("/city2", CityController.listCityCountry)
                .get("/city/search/<page>", CityController.listCityCountryWithPagination)
                .get("/city/<city_id>", CityController.getCityCountryById)
                .post("/city/bind", CityController.postCityBind)
                .put("/city/bind/update", CityController.updateCityBind)
                .start(7070);
    }
}