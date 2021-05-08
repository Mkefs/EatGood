package dev.cotapro.mx.api_ingredientes;

import java.io.IOException;

import dev.cotapro.mx.api.Recetas;
import retrofit2.Call;
import retrofit2.Response;

public class Datos {
    static public Imagen getimage(ApiManagement.API_INTERACION api){
       String nombre = "";
        Call<Imagen> consulta = api.imagen_json(nombre);
        try {
            Response<Imagen> respuesta = consulta.execute();
            return respuesta.body();
        }catch (IOException exception){
            return null;
        }
    }
    static public Nombres getnombres(ApiManagement.API_INTERACION api){
        Call<Nombres> consulta = api.nombres_json();
        try {
            Response<Nombres> respuesta = consulta.execute();
            return respuesta.body();
        }catch (IOException exception){
            return null;
        }
    }

}
