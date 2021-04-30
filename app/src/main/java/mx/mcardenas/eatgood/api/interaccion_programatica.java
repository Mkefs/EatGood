package mx.mcardenas.eatgood.api;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class interaccion_programatica {
	// En esa parte se le da la referencia a la api generada por retrofit
	static public Recetas conseguir_feed(ApiManagement.API_INTERACTION api) {
		Call<Recetas> consulta = api.feed_json();
		try {
			Response<Recetas> respuesta = consulta.execute();
			return respuesta.body();
		} catch (IOException exception) {
			return null;
		}
	}
}
