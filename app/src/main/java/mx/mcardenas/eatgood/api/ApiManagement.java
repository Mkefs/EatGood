package mx.mcardenas.eatgood.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiManagement {
    public interface  API_INTERACTION{
        @GET("https://gr.kiwilimon.com/v6")
        Call<String>feed_json(
                @Query("language")String language,
                @Query("device")String device);
    }
}
