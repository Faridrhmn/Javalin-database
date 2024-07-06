package id.dojo.controller;

import id.dojo.model.Actor;
import io.javalin.http.Handler;

import com.google.gson.Gson;

import static id.dojo.model.Actor.*;

public class ActorController {
    static Gson gson = new Gson();

    public static Handler listActorApi = ctx -> ctx.json(Actor.dataActor());

    public static Handler postActorApi = ctx->{
        Actor actor = ctx.bodyAsClass(Actor.class);
        String firstName = actor.getFirst_name();
        String lastName = actor.getLast_name();
        System.out.println(actor + "dengan nama : " + firstName + " and " + lastName);
        ctx.json(gson.toJson(makeActor(firstName, lastName)));
        ctx.status(201);
    };

    public static Handler getActorIdApi = ctx -> {
        Integer actor_id = Integer.valueOf(ctx.pathParam("actor_id"));
        ctx.json(gson.toJson(dataActorById(actor_id)));
    };

    public static Handler updateActorApi = ctx->{
        Actor actor = ctx.bodyAsClass(Actor.class);
        Integer actorId = actor.getActor_id();
        String firstName = actor.getFirst_name();
        String lastName = actor.getLast_name();
        ctx.json(gson.toJson(changeActor(actorId,firstName,lastName)));
        ctx.status(201);
    };

    public static Handler deleteActorApi = ctx->{
        String actor_id = ctx.pathParam("actor_id");
        ctx.json(gson.toJson(deleteActor(actor_id)));
    };
}
