package com.communifydam.app.communify;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    ListView lv;


    private static Integer[] images = {
            android.R.drawable.ic_btn_speak_now,
            android.R.drawable.ic_lock_idle_low_battery,
            android.R.drawable.ic_menu_crop,
            android.R.drawable.ic_menu_call,
            android.R.drawable.ic_menu_day,
            android.R.drawable.ic_media_ff,
            android.R.drawable.ic_media_pause,
            android.R.drawable.ic_menu_sort_by_size
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);



        refrescaLista();

        YoYo.with(Techniques.Pulse)
                .duration(900)
                .repeat(YoYo.INFINITE)
                .playOn(findViewById(R.id.fabAddAnuncio));

        ImageButton btnComunidades = (ImageButton) findViewById(R.id.btnComunidades);
        btnComunidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pintaComunidades();
            }
        });

        ImageButton btnPerfil = (ImageButton) findViewById(R.id.btnPerfil);
        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
                startActivity(intent);
            }
        });


    }





    public void pintaComunidades() {

        String[] comunidades = {"Mi barrio", "13 Rue del Percebe"};


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.lista_com_dialog, null);
        dialogBuilder.setView(dialogView);


        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


    }

    public void refrescaLista() {
        lv = (ListView) findViewById(R.id.lvAnuncios);
        ArrayList<Anuncio> anuncios = new ArrayList<Anuncio>();

        String[] titles = {"Anuncio 1","Anuncio 2","Anuncio 3","Anuncio 4","Anuncio 5","Anuncio 6","Anuncio 7","Anuncio 8"};
        String[] descriptions = {"Publicado en Comunidad DAM","Publicado en Comunidad DAM","Publicado en Comunidad DAM","Publicado en Comunidad DAM","Publicado en Comunidad DAM",
                "Publicado en Comunidad DAM","Publicado en Comunidad DAM","Publicado en Comunidad DAM"};

        //Populate the List
        for (int i = 0; i < titles.length; i++) {
            Anuncio item = new Anuncio(images[i], titles[i], descriptions[i]);
            anuncios.add(item);
        }

        // Set the adapter on the ListView
        AdaptadorAnuncio adapter = new AdaptadorAnuncio(getApplicationContext(), R.layout.plantilla_anuncio, anuncios);
        lv.setAdapter(adapter);
    }

}
