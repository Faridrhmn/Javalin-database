package id.dojo.model;

import com.google.gson.Gson;
import id.dojo.helper.DBUtils;
import id.dojo.helper.Res;
import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import static id.dojo.DbConnection.sql2o;

public class City {
    private Integer city_id;
    private String city;
    private Integer country_id;
    private Timestamp last_update;
    private Country country;
    static Gson gson = new Gson();

    public City() {
    }

    public City(int i, String city, Integer country_id) {
        this.city = city;
        this.country_id = country_id;
    }

    public City(int i, String city, Integer country_id, Integer city_id) {
        this.city = city;
        this.country_id = country_id;
        this.city_id = city_id;
    }

    public Integer getCountry_id() {
        return country_id;
    }

    public String getCity() {
        return city;
    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public static Res<List<City>> dataCity(){
        Res<List<City>> res = new DBUtils<City>().list(
                "select city_id,city,last_update from city limit 10", City.class
        );
        return res;
    }

    public static Res<City> dataCityById(Integer city_id){
        Res<City> res = new DBUtils<City>().get("select city_id,city,last_update from city WHERE city_id= :p1;", city_id, City.class);
        return res;
    }

    public static Res<List<City>> listCity(Map<String,String> params, Integer page){
        Integer offset = 0;
        if(page != null || page > 0){
            offset = (page-1)*10;
        }
        String sql = "select city_id,city,last_update from city WHERE TRUE";
        String where = "";
        if(params.get("city") != null && params.get("city") != ""){
            where += " AND city ILIKE '%" + params.get("city") + "%'";
        }
        Res data = new DBUtils().list(
                sql + where + " ORDER BY city_id LIMIT 10 OFFSET " + offset,
                City.class
        );
        return data;
    }

    public static String postCity(City city){
        Res data = new DBUtils().insert(
                "INSERT INTO city (city, country_id)" +
                        "VALUES ( :city, :country_id )", city
        );
        return gson.toJson(data);
    }

    public static String changeCity(City city) {
        Res data = new DBUtils().update(
                "UPDATE city " +
                        "SET city = :city, country_id = :country_id " +
                        "WHERE city_id = :city_id", city
        );
        return gson.toJson(data);
    }
}
