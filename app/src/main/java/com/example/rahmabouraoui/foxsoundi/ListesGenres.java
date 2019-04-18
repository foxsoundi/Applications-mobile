package com.example.rahmabouraoui.foxsoundi;

import java.util.ArrayList;
import java.util.List;

public class ListesGenres {

    private String nomArtistes;
    private String titreAlbum;
    private List<String> genres = new ArrayList<String>();

    public ListesGenres(String nomArtistes, String titreAlbum)  {
        this.nomArtistes= nomArtistes;
        this.titreAlbum= titreAlbum;
    }



    public String getNomArtistes() {
        return nomArtistes;
    }

    public void setNomArtistes(String nomArtistes) {
        this.nomArtistes = nomArtistes;
    }

    public String getTitreAlbums() {
        return titreAlbum;
    }

    public void setTitreAlbum(String titreAlbum) {
        this.titreAlbum = titreAlbum;
    }

    public List<String> getGenres() {
        return genres;
    }

    @Override
    public String toString()  {
        return nomArtistes;
    }
}
