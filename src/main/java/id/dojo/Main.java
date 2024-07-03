package id.dojo;
import com.google.gson.Gson;
import id.dojo.controller.ActorController;

import io.javalin.Javalin;
import static id.dojo.model.Payment.dataPayment;

public class Main {
    static Gson gson = new Gson();
    public static void main(String[] args) {

        var app = Javalin.create()
                .get("/actors", ActorController.listActorApi)
                .get("/payments", ctx -> ctx.json(gson.toJson(dataPayment())))
                .get("/actor/<actor_id>", ActorController.getActorIdApi)
                .post("/actor", ActorController.postActorApi)
                .put("/actor", ActorController.updateActorApi)
                .delete("/actor/<actor_id>", ActorController.deleteActorApi)
                .start(7070);
    }
}