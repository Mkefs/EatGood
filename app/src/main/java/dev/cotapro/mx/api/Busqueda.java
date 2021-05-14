package dev.cotapro.mx.api;

public class Busqueda{
    //Definimos los parametros que hay dentro del contenedor search que nosotros declaramos como Busqueda
    public int quantity, page, total, time;
    public boolean more;
    public Descripcion [] payload;
    //El parametro Descripcion es declarado en su propia clase Descripcion
}

