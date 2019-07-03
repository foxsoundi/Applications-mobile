package com.app.rahmabouraoui.foxsoundi;

import java.io.Serializable;

public class Genre implements Serializable {

    private String href;
    private String id;
    private String name;
    private Icone icone;

    public Genre() {
    }

    public Genre(String href, String id, String name, Icone icone) {
        this.href = href;
        this.id = id;
        this.name = name;
        this.icone = icone;
    }

    public String getHref() {
        return href;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Icone getIcone() {
        return icone;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIcone(Icone icone) {
        this.icone = icone;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "href='" + href + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", icone=" + icone +
                '}';
    }
}
