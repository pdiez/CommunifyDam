package com.communifydam.app.communify;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 2912 on 06/02/2018.
 */

public class FragmentAddAnuncio_2 extends Fragment {

    private TextView titulo;
    private TextView descripcion;
    private TextView escoge_com;

    private EditText edit_titulo;
    private EditText edit_descripcion;
    private Spinner sp_com;
    private ArrayList<String> lista_comunidades;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_anuncio_step_2, container, false);

        titulo = (TextView)v.findViewById(R.id.text_agregar);
        descripcion= (TextView)v.findViewById(R.id.text_descripcion);

        edit_titulo=(EditText)v.findViewById(R.id.et_title);
        edit_descripcion=(EditText)v.findViewById(R.id.et_descripcion);
        sp_com = (Spinner)v.findViewById(R.id.spinner_com);


        ViewPager vp=(ViewPager) this.getParentFragment().getView().findViewById(R.id.dgPager);
        AdaptadorPagerAddAnuncio mi_vp=(AdaptadorPagerAddAnuncio)vp.getAdapter();
        mi_vp.setEdit_titulo(edit_titulo);
        mi_vp.setEdit_descripcion(edit_descripcion);
        mi_vp.setSpinner_com(sp_com);

        rellenar_comunidades();

        return v;
    }

    public void rellenar_comunidades(){

        Bundle b= this.getArguments();
        lista_comunidades = new ArrayList<String>();
        lista_comunidades=b.getStringArrayList("comunidades");
        //lista_comunidades = getArguments().getStringArrayList("comunidades");
        sp_com.setAdapter(null);
        ArrayAdapter<String> adaptador = new ArrayAdapter <String>(getContext(),
                android.R.layout.simple_spinner_item, lista_comunidades);

        sp_com.setAdapter(adaptador);

    }
}

