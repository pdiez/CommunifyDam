package com.communifydam.app.communify;

/**
 * Created by 2912 on 16/01/2018.
 */

public class Anuncio {

    private int imageId;
    private String title;
    private String description;
    private String numero;
    private String anunciante;
    private String fecha;

    public Anuncio(int imageId, String title, String desc) {
        this.imageId = imageId;
        this.title = title;
        this.description = desc;
        this.numero = "0";
        this.anunciante ="prueba de anunciante";
        this.fecha = "hace 15 min.";
    }
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    public String getDesc() {
        return description;
    }
    public void setDesc(String desc) {
        this.description = desc;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getNumero() {return numero; }
    public String getAnunciante() { return anunciante; }
    public String getFecha() { return fecha; }
    @Override
    public String toString() {
        return title + "\n" + description;
    }

}
