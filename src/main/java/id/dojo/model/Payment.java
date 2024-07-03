package id.dojo.model;

import id.dojo.DbConnection;
import id.dojo.helper.Res;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.sql.Timestamp;
import java.util.List;

import static id.dojo.DbConnection.sql2o;

public class Payment {
    private int payment_id;
    private int customer_id;
    private int staff_id;
    private int rental_id;
    private float amount;
    private Timestamp payment_date;

    public String toString(){
        return "id payment = " + this.getPayment_id() + " id customer : " + this.getCustomer_id() + " id staff: " + this.getStaff_id() + " id rental : " + this.getRental_id() + " amount : " + this.getAmount() + " payment date : " + this.getPayment_date() + "\n";
    }

    public int getPayment_id() {
        return payment_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public int getRental_id() {
        return rental_id;
    }

    public float getAmount() {
        return amount;
    }

    public Timestamp getPayment_date() {
        return payment_date;
    }

    public static Res<List<Payment>> dataPayment(){
        try(Connection con = sql2o.open()){
            List<Payment> payments = con.createQuery("select payment_id,customer_id,staff_id,rental_id,amount,payment_date from payment limit 10").executeAndFetch(Payment.class);
            System.out.println(payments);
            Res<List<Payment>> res1 = new Res<List<Payment>>("Berhasil ambil data payment", payments);
            return res1;
        }
    }
}
