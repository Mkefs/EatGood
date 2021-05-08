package dev.cotapro.mx.api_ingredientes;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class Datos {
    static public String[][] getnombres(ApiManagement.API_INTERACION api){
        Call<String[][]> consulta = api.nombres_json();
        try {
            Response<String[][]> respuesta = consulta.execute();
            return respuesta.body();
        }catch (IOException exception){
            return null;
        }
    }

}
