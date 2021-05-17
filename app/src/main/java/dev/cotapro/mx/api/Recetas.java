package dev.cotapro.mx.api;

public class Recetas{
    //Declaramos los parámetros que hay dentro del contenedor feed que nosotros declaramos como Recetas
    public int key, page, quantity, time, total;
    public boolean more;
    String type;
    public Descripcion[] payload;
}

