package com.communifydam.app.communify;

/**
 * Created by Usuario DAM on 26/02/2018.
 */

public class Intercambador implements Intercambio {

    public Anuncio anuncio_guardado;

    @Override
    public void guardarObjetoAnuncio(Anuncio anuncio) {
        anuncio_guardado = anuncio;
    }

    @Override
    public Anuncio obtenerObjetoAnuncio() {
        return anuncio_guardado;
    }
}
