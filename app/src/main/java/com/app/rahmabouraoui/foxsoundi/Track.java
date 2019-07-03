package com.app.rahmabouraoui.foxsoundi;

import java.io.Serializable;

public class Track implements Serializable {

    private String name;
    private String artiste ;
    private String id;
    private String urlImage;

    public Track() {
    }

    @Override
    public String toString() {
        return "Track{" +
                "name='" + name + '\'' +
                ", artiste='" + artiste + '\'' +
                ", id='" + id + '\'' +
                ", urlImage='" + urlImage + '\'' +
                '}';
    }

    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

}
