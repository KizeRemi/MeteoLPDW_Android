package kize.meteolpdw;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by mavillaz on 18/03/2016.
 */
public interface API {
    public static final String APPID = "b1b15e88fa797225412429c1c50c122a";

    @GET("weather")
    Call<Item> getLastMessage(@Query("q") String name );
    @GET("{id}/messages.json")
    Call<Item> getLastMessage(@Path("id") int userId);
}
