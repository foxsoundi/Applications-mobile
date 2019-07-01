package com.example.rahmabouraoui.foxsoundi;

public class Playlist {

    private  String name ;
    private String urlImage;
    private  String id;
    private  String href;

    public Playlist() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getName() {
        return name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getId() {
        return id;
    }

    public String getHref() {
        return href;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "name='" + name + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", id='" + id + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
