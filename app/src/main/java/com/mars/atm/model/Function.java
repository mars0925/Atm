package com.mars.atm.model;

public class Function {
    private String name;
    private int imageId;

    public Function() {
    }

    public Function(String name) {
        this.name = name;
    }

    public Function(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
