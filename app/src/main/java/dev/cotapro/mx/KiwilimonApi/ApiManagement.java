package dev.cotapro.mx.KiwilimonApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiManagement {
    public interface  API_INTERACTION {
        @GET("feed?language=es&device=android&type=home&v=1")
        Call<RecetasEntity> feed_json(
            @Query("page") int pagina
        );

        @GET("search?language=es&device=android&quantity=10&v=1")
        Call<RecetasEntity> search_json(
                @Query("q") String busqueda,
                @Query("page") int pagina
        );

        @GET("recipe?language=es&device=android&v=1")
        Call<RecetaEntity> recipe_json(
            @Query("key") long key
        );
    }
}
