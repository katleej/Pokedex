package com.hfad.pokedex;

public class Model {

    private int imageId;
    private String name, type;

    public int getImage() {
        return imageId;
    }

    public void setImage(String name) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
