package mx.mcardenas.eatgood.api;

import mx.mcardenas.eatgood.MainActivity;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @GET("feed")
    Call<Info> getFeed(
            @Query("language") String language,
            @Query("device") String device
    );
}
