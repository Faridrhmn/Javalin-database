package id.dojo.model;

import id.dojo.helper.DBUtils;
import id.dojo.helper.Res;
import java.sql.Timestamp;
import java.util.List;

public class Country {
    private Integer country_id;
    private String country;
    private Timestamp last_update;

    public static Res<Country>getCountryFromCity(Integer city_id){
        Res<Country> res = new DBUtils<Country>().get("SELECT co.country_id, co.country, co.last_update FROM city ci JOIN country co ON ci.country_id = co.country_id WHERE ci.city_id= :p1;", city_id, Country.class);
        return res;
    }
}
