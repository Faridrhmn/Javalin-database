package id.dojo.helper;

public class Res<T> {
    private String message;
    private T data;

    public Res(String massage, T data){
        this.message = massage;
        this.data = data;
    }
}
