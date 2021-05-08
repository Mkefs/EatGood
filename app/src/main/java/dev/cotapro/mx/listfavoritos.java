package dev.cotapro.mx;

public class listfavoritos {
    public String color;
    public String name;
    public String autor;

    public listfavoritos(String color, String name, String autor) {
        this.color = color;
        this.name = name;
        this.autor = autor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
