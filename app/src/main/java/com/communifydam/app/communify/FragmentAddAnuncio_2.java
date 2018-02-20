package com.communifydam.app.communify;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by 2912 on 06/02/2018.
 */

public class FragmentAddAnuncio_2 extends Fragment {

    private TextView titulo;
    private TextView descripcion;
    private EditText edit_titulo;
    private EditText edit_descripcion;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_anuncio_step_2, container, false);

        titulo = (TextView)v.findViewById(R.id.text_agregar);
        descripcion= (TextView)v.findViewById(R.id.text_descripcion);

        edit_titulo=(EditText)v.findViewById(R.id.et_title);
        edit_descripcion=(EditText)v.findViewById(R.id.et_descripcion);

        ViewPager vp=(ViewPager) this.getParentFragment().getView().findViewById(R.id.dgPager);
        AdaptadorPagerAddAnuncio mi_vp=(AdaptadorPagerAddAnuncio)vp.getAdapter();
        mi_vp.setEdit_titulo(edit_titulo);
        mi_vp.setEdit_descripcion(edit_descripcion);

        return v;
    }
}

