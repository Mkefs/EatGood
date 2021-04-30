package mx.mcardenas.eatgood.api;

public class Busqueda{
    public int quantity, page, total, time;
    public boolean more;
    public Descripcion [] payload;
    public static String getingredientes(String[] ingredientes){
        String concatenar_ingredientes = "";
        for(int i = 0; i<=ingredientes.length; i++){
            concatenar_ingredientes = concatenar_ingredientes + ingredientes[i];
        }
        return concatenar_ingredientes;
    }

}

