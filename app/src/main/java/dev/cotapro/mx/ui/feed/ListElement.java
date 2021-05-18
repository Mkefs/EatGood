package dev.cotapro.mx.ui.feed;

import android.widget.ImageView;

public class ListElement {
    public ImageView image;
    public String platillo;
    public String autor;
    public String stars;

    public ListElement(ImageView image, String platillo, String autor, String stars) {
        this.image = image;
        this.platillo = platillo;
        this.autor = autor;
        this.stars = stars;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
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
