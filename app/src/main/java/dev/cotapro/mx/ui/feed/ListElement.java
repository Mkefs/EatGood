package dev.cotapro.mx.ui.feed;

public class ListElement {
    public String color;
    public String platillo;
    public String autor;
    public String stars;

    public ListElement(String color, String platillo, String autor, String stars) {
        this.color = color;
        this.platillo = platillo;
        this.autor = autor;
        this.stars = stars;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlatillo() {
        return platillo;
    }

    public void setPlatillo(String platillo) {
        this.platillo = platillo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }
}
