package mx.mcardenas.eatgood.api;

import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;

public class Datos {
    static public Recetas getfeed(ApiManagement.API_INTERACTION api) {
        Call<Recetas> consulta = api.feed_json();
        try {
            Response<Recetas> respuesta = consulta.execute();
            return respuesta.body();
        } catch (IOException exception) {
            return null;
        }
    }
    static public Busqueda getsearch(ApiManagement.API_INTERACTION api) {
        Call<Busqueda> consulta = api.search_json();
        try {
            Response<Busqueda> respuesta = consulta.execute();
            return respuesta.body();
        } catch (IOException exception) {
            return null;
        }
    }
}
