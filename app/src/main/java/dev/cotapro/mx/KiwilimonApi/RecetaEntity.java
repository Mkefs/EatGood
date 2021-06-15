package dev.cotapro.mx.KiwilimonApi;

public class RecetaEntity {
    public static class Ingredientes {
        public String text;
    }
    public static class Pasos {
        public String text;
    }
    public int calories,favorites, key, cooked, cooktime;
    public String titleh1, description, image, published;
    public float raiting;
    public Pasos[] steps;
    public Ingredientes[] ingredients;
    public ChefEntity[] images;
}
