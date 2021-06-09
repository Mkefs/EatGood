package dev.cotapro.mx.KiwilimonApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiManagement {
    public interface  API_INTERACTION {
        @GET("feed?language=es&device=android&type=recetaclasificacion&v=1&key=1000")
        Call<ResponseBody> feed_json(
            @Query("page") int pagina
        );

        @GET("search?language=es&device=android&page=1&quantity=10")
        Call<ResponseBody> search_json(
                @Query("q") String busqueda,
                @Query("page") int pagina
        );

        @GET("recipe?language=es&device=android")
        Call<ResponseBody> recipe_json(
            @Query("key") long key
        );
    }
}
//comentario para porbar mi contra
