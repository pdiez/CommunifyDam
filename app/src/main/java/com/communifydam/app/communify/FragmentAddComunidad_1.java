package com.communifydam.app.communify;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by 2912 on 06/02/2018.
 */

public class FragmentAddComunidad_1 extends Fragment {

    private EditText et_nombre_comunidad;
    ViewPager vp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_comunidad_step_1, container, false);
        vp = (ViewPager) this.getParentFragment().getView().findViewById(R.id.dgPagerComunidad);
        AdaptadorPagerAddComunidad mi_vp = (AdaptadorPagerAddComunidad) vp.getAdapter();
        mi_vp.setEt_nombre_comunidad(et_nombre_comunidad);


        return v;
    }




    }

