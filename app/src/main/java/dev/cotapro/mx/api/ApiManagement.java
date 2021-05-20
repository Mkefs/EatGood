package dev.cotapro.mx.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiManagement {
    //Realizamos la consulta a la URL que definimos en el parámetro baseURL de nuestra clase Retrofit
    public interface  API_INTERACTION {
        //Consulta para el contenedor feed (recetas)
        @GET("feed?language=es&device=android&type=recetaclasificacion&v=1&key=1000")
        //La consulta se guarda en el objeto Recetas
        Call<Recetas> feed_json(
            @Query("page") int pagina
        );
        //consulta para el contenedor search (busqueda)
        @GET("search?language=es&device=android&human=1&page=1&quantity=10")
        //La consulta se guarda en el objeto Busqueda
        Call<Busqueda> search_json(
                //Hay dos objetos dentro del contenedor (busqueda y pagina)
                @Query("q") String busqueda,
                @Query("page") int pagina
        );
        //Consulta para el contenedor recipe (receta)
        @GET("recipe?language=es&device=android&human=1&path=/chef/7036255978&key=13")
        Call<Receta> recipe_json();
    }
}
