package com.communifydam.app.communify;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * Created by 2912 on 26/01/2018.
 */


public class AnuncioActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private String id_anuncio;
    Button button_Prediccion;
    Anuncio anuncio;
    Intercambio i_i;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        button_Prediccion = (Button) findViewById(R.id.btn_aceptar);

        anuncio = i_i.obtenerObjetoAnuncio();

        obtenerAnuncio(database, id_anuncio);

        button_Prediccion.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                String paso;
            }
        });
    }
    //Par de metodos para buscar el anuncio
    public void obtenerAnuncio(FirebaseDatabase database, String id_anuncio){
        DatabaseReference database_ref = (DatabaseReference) database.getReference("anuncios").child(id_anuncio).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Anuncio anuncio_clicado = dataSnapshot.getValue(Anuncio.class);
                anuncioObtenido(anuncio_clicado);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public Anuncio anuncioObtenido(Anuncio anuncio_clicado){
        anuncio = anuncio_clicado;
        return anuncio_clicado;
    }
    //Par de metodos para buscar el anuncio




    public void anadirParticipante(String participante){
        ArrayList<String> lista_participantes = anuncio.getLista_participantes();
        lista_participantes.add(participante);
        anuncio.setLista_participantes(lista_participantes);
    }

}
