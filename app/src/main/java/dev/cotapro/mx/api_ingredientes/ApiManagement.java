package dev.cotapro.mx.api_ingredientes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiManagement {
    public interface API_INTERACION{
        @GET("ingredientes")
        Call<String[][][]> nombres_json();
    }
}
