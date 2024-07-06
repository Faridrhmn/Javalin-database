package id.dojo.model;

import com.google.gson.Gson;
import id.dojo.DbConnection;
import id.dojo.helper.DBUtils;
import id.dojo.helper.Res;
import org.sql2o.Sql2o;

import java.sql.Timestamp;
import java.util.List;

public class Inventory {
    private Integer inventory_id;
    private Integer film_id;
    private Integer store_id;
    private Timestamp last_update;
    private Film film;

    static Sql2o sql2o = DbConnection.getSql2o();
    static Gson gson = new Gson();

    public Integer getFilm_id() {
        return film_id;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public static Res<List<Inventory>> listInventoryFilm(){
        Res<List<Inventory>> res = new DBUtils<Inventory>().list(
                "SELECT inv.inventory_id, inv.film_id, inv.store_id, inv.last_update FROM inventory inv JOIN film f ON inv.film_id = f.film_id LIMIT 5", Inventory.class
        );
        return res;
    }
}
