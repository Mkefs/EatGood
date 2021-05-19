package dev.cotapro.mx.ui.feed;

public class ListElement {
    public String image;
    public String platillo;
    public String autor;
    public String stars;

    public ListElement(String image, String platillo, String autor, String stars) {
        this.image = image;
        this.platillo = platillo;
        this.autor = autor;
        this.stars = stars;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
