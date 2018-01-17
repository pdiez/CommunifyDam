package com.communifydam.app.communify;

/**
 * Created by 2912 on 17/01/2018.
 */

public class Comunidad {
    private boolean marcado;
    private String title;
    private String description;

    public Comunidad(boolean marcado, String title, String desc) {
        this.marcado = marcado;
        this.title = title;
        this.description = desc;
    }
    public boolean getMarcado() {
        return marcado;
    }
    public void setMarcado(boolean marca) {
        this.marcado = marca;
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
    @Override
    public String toString() {
        return title + "\n" + description;
    }

}
