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
    private String estado;
    private String comunidad;
    private String id_comunidad;

    public Anuncio(int imageId, String title, String desc) {
        this.imageId = imageId;
        this.title = title;
        this.description = desc;
        this.numero = numero;
        this.anunciante = anunciante;
        this.fecha = fecha;
    }

    public Anuncio(){

    }
    public String getId_comunidad() {
        return id_comunidad;
    }

    public void setId_comunidad(String id_comunidad) {
        this.id_comunidad = id_comunidad;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAnunciante() {
        return anunciante;
    }

    public void setAnunciante(String anunciante) {
        this.anunciante = anunciante;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    public void cambiarEstado (String nuevo_estado){
        this.estado = nuevo_estado;
    }

    public Anuncio(int imageId, String title, String description, String numero, String anunciante, String fecha, String estado, String comunidad) {
        this.imageId = imageId;
        this.title = title;
        this.description = description;
        this.numero = numero;
        this.anunciante = anunciante;
        this.fecha = fecha;
        this.estado = estado;
        this.comunidad = comunidad;
    }
}