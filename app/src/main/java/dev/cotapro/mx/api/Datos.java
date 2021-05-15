package dev.cotapro.mx.api;

import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;
//Dentro de esta clase ejecutaremos de manera síncrona la consulta
public class Datos {
    //Método que devuelve las Recetas en la respuesta.body()
    static public Recetas getfeed(ApiManagement.API_INTERACTION api) {
        Call<Recetas> consulta = api.feed_json(8);
        try {
            Response<Recetas> respuesta = consulta.execute();
            return respuesta.body();
        } catch (IOException exception) {
            return null;
        }
    }
    //Método que devuelve las Busqueda en la respuesta.body()
    static public Busqueda getsearch(ApiManagement.API_INTERACTION api, String[] ingrediente) {
        //Hacemos un ciclo for que utilice el argumento String[] ingrediente (ingresado por el usuario) para definir la busqueda en el String ingredientes
        String ingredientes = "";
        for(int i = 0; i<=ingrediente.length; i++){
            ingredientes = ingredientes + ingrediente[i];
        }
        //El método devuelve la Busqueda con el contenido del String ingredientes procesado en el for anterior y el page que se encuentra en ApiManagement en la respuesta.body()
        Call<Busqueda> consulta = api.search_json(ingredientes, 1);
        try {
            Response<Busqueda> respuesta = consulta.execute();
            return respuesta.body();
        } catch (IOException exception) {
            return null;
        }
    }
    //Método que devuelve las Receta en la respuesta.body()
    static public Receta getrecipe(ApiManagement.API_INTERACTION api){
        Call<Receta> consulta = api.recipe_json();
        try {
            Response<Receta> respuesta = consulta.execute();
            return respuesta.body();
        } catch (IOException exception) {
            return null;
        }

    }



}
