package dev.cotapro.mx.api;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
public class Datos {
    static public String getfeed(ApiManagement.API_INTERACTION api, int page) {
        Call<ResponseBody> consulta = api.feed_json(page);
        try {
            Response<ResponseBody> respuesta = consulta.execute();
            return respuesta.body().string();
        } catch (IOException exception) {
            return null;
        }
    }
    static public String getsearch(ApiManagement.API_INTERACTION api, String[] ingrediente) {
        String ingredientes = "";
        for(int i = 0; i<=ingrediente.length; i++){
            ingredientes = ingredientes + ingrediente[i];
        }
        Call<ResponseBody> consulta = api.search_json(ingredientes, 1);
        try {
            Response<ResponseBody> respuesta = consulta.execute();
            return respuesta.body().string();
        } catch (IOException exception) {
            return null;
        }
    }
    static public String getrecipe(ApiManagement.API_INTERACTION api, int key){
        Call<ResponseBody> consulta = api.recipe_json(key);
        try {
            Response<ResponseBody> respuesta = consulta.execute();
            return respuesta.body().string();
        } catch (IOException exception) {
            return null;
        }
    }



}
