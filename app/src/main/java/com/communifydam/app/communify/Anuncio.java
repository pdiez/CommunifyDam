package com.communifydam.app.communify;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by 2912 on 16/01/2018.
 */

public class Anuncio {

   private String titulo;
   private String descripcion;
   private String fecha;
   private String expira;
   private String userId;
   private String communityId;
   private String imagen;
   private String id_firebase;
   private ArrayList<String> lista_participantes;
   private int tipo; // 0 - Ofrezco 1 - Necesito

    public String getExpira() {
        return expira;
    }

    public void setExpira(String expira) {
        this.expira = expira;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public ArrayList<String> getLista_participantes() {
        return lista_participantes;
    }

    public void setLista_participantes(ArrayList<String> lista_participantes) {
        this.lista_participantes = lista_participantes;
    }

    public String getId_firebase() {
        return id_firebase;
    }

    public void setId_firebase(String id_firebase) {
        this.id_firebase = id_firebase;
    }

    public Anuncio() {

    }

}