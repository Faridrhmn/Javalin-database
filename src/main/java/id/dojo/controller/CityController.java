package id.dojo.controller;

import com.google.gson.reflect.TypeToken;
import id.dojo.helper.Res;
import id.dojo.model.Actor;
import id.dojo.model.City;
import id.dojo.model.Country;
import io.javalin.http.Handler;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static id.dojo.model.City.*;

public class CityController {
    static Gson gson = new Gson();
    public static Handler listDataCity = ctx -> ctx.json(gson.toJson(City.dataCity()));

    public static Handler listCityCountry = ctx -> {
        Res<List<City>> list = City.dataCity();

        List<City> listCity = list.getData();
        for(City city : listCity){
            Country hehe = Country.getCountryFromCity(city.getCity_id()).getData();
            city.setCountry(hehe);
            System.out.println(city.getCity_id());
        }
        ctx.json(gson.toJson(list));
    };

    public static Handler listCityCountryWithPagination = ctx -> {
        Map<String, String> paramList = new HashMap<>();
        String page = ctx.pathParam("page");
        String city = ctx.queryParam("city");
        paramList.put("city", city);
        Res<List<City>> res = City.listCity(paramList, Integer.valueOf(page));
        List<City> listCity = res.getData();
        for(City wkwk : listCity){
            Country hehe = Country.getCountryFromCity(wkwk.getCity_id()).getData();
            wkwk.setCountry(hehe);
            System.out.println(wkwk.getCity_id());
        }
        ctx.json(gson.toJson(res));
    };

    public static Handler getCityCountryById = ctx -> {
        Integer city_id = Integer.valueOf(ctx.pathParam("city_id"));
        Res<City> res = City.dataCityById(city_id);
        City ehe = res.getData();
        Country lah = Country.getCountryFromCity(city_id).getData();
        ehe.setCountry(lah);
        ctx.json(gson.toJson(res));
    };

    public static Handler postCityBind = ctx -> {
        Map<String, Object> payload = gson.fromJson(ctx.body(), new TypeToken<Map<String,Object>>(){}.getType());
        Object cityname = payload.get("city");
        Object countryId = payload.get("country_id");
        City cityne = new City(-1, cityname.toString(), ((Double) countryId).intValue());
        System.out.println(cityne);
        try{
            ctx.json(
                    City.postCity(cityne)
            );
        } catch (Exception e){
            e.printStackTrace();
            ctx.status(500).json(gson.toJson(new Res(e.getMessage(), "")));
        }
    };

    public static Handler updateCityBind = ctx -> {
        Map<String, Object> payload = gson.fromJson(ctx.body(), new TypeToken<Map<String,Object>>(){}.getType());
        Object cityname = payload.get("city");
        Object countryId = payload.get("country_id");
        Object cityId = payload.get("city_id");
        City cityne = new City(-1, cityname.toString(), ((Double) countryId).intValue(), ((Double) cityId).intValue());
        System.out.println(cityne);
        try{
            ctx.json(
                    City.changeCity(cityne)
            );
        } catch (Exception e){
            e.printStackTrace();
            ctx.status(500).json(gson.toJson(new Res(e.getMessage(), "")));
        }
    };
}
