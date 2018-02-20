package com.communifydam.app.communify;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by 2912 on 06/02/2018.
 */

public class FragmentAddComunidad_2 extends Fragment {

    private EditText et_descripcion_comunidad;
    private GrabarAnuncio ga;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_comunidad_step_2, container, false);
        ViewPager vp=(ViewPager) this.getParentFragment().getView().findViewById(R.id.dgPagerComunidad);
        AdaptadorPagerAddComunidad mi_vp=(AdaptadorPagerAddComunidad)vp.getAdapter();
        ga=(GrabarAnuncio)mi_vp;//Está por ver


        return v;
    }




    }

