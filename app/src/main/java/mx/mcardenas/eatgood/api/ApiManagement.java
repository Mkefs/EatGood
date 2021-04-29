package mx.mcardenas.eatgood.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiManagement {
    public interface  API_INTERACTION {
        @GET("feed?language=es&device=android&type=recetaclasificacion&v=1&key=1000")
        Call<Recetas> feed_json();
        @GET("search?language=es&device=android&type=search")
        Call<Busqueda> search_json();
    }
}
