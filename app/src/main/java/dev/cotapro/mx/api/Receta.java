package dev.cotapro.mx.api;

public class Receta {
    public class Ingredientes {
        public String text;
    }
    public class Pasos {
        public String text;
    }
    public int calories,favorites, key, cooked, cooktime;
    public String titleh1, description, image, published;
    public float raiting;
    public Pasos[] steps;
    public Ingredientes[] ingredients;
    public Chef[] images;
}
