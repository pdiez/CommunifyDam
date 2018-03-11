package com.communifydam.app.communify;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

/**
 * Created by Jorge on 06/02/2018.
 */

public class Usuario {

    private String emailUsuario;
    private String nombre;
    private String imagen;
    private ArrayList<String> comunidades;

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Usuario() {

    }

    public ArrayList<String> getComunidades() {
        return comunidades;
    }

    public void setComunidades(ArrayList<String> comunidades) {
        this.comunidades = comunidades;
    }

    public Usuario(String email) {
        this.emailUsuario = email;
        this.nombre = "";
        this.imagen =  "@drawable/ic_people";
        this.comunidades = new ArrayList<String>();
    }
}