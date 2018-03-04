package com.communifydam.app.communify;

/**
 * Created by Usuario DAM on 26/02/2018.
 */

public interface Intercambio {

    public Anuncio objeto_anuncio = null;

    public void guardarObjetoAnuncio(Anuncio anuncio);

    public Anuncio obtenerObjetoAnuncio();

}
