package id.dojo.controller;

import com.google.gson.reflect.TypeToken;
import id.dojo.helper.Res;
import id.dojo.model.Actor;
import io.javalin.http.Handler;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static id.dojo.model.Actor.*;

public class ActorController {
    static Gson gson = new Gson();

    public static Handler listActorApi = ctx -> ctx.json(gson.toJson(Actor.dataActor()));

    public static Handler getListActorApinya = ctx -> {
        Map<String, String> paramList = new HashMap<>();

        String page = ctx.pathParam("page");
        String firstname = ctx.queryParam("firstname");
        String lastname = ctx.queryParam("lastname");

        paramList.put("firstname", firstname);
        paramList.put("lastname", lastname);

        Res<List<Actor>> res = Actor.listActor(paramList, Integer.valueOf(page));

        ctx.json(gson.toJson(res));
    };

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

    public static Handler postActorBind = ctx -> {
        Map<String, Object> payload = gson.fromJson(ctx.body(), new TypeToken<Map<String,Object>>(){}.getType());
        Object firstname = payload.get("first_name");
        Object lastname = payload.get("last_name");
        Actor actor = new Actor(-1, firstname.toString(), lastname.toString());
        System.out.println(actor);
        try{
            ctx.json(
                    Actor.postActor(actor)
            );
        } catch (Exception e){
            e.printStackTrace();
            ctx.status(500).json(gson.toJson(new Res(e.getMessage(), "")));
        }
    };
}
