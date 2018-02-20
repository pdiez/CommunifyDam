package com.communifydam.app.communify;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

/**
 * Created by GABBERCORE on 10/02/2018.
 */

public class FragmentAddAnuncio_5 extends Fragment{

    private TimePicker tm;
    private TextView tv_hora;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_anuncio_step_4, container, false);

        tm = (TimePicker)v.findViewById(R.id.selector_hora);
        tv_hora= (TextView)v.findViewById(R.id.text_selec_hora);

        ViewPager vp=(ViewPager) this.getParentFragment().getView().findViewById(R.id.dgPager);
        AdaptadorPagerAddAnuncio mi_vp=(AdaptadorPagerAddAnuncio)vp.getAdapter();
        mi_vp.setTp(tm);

        return v;
    }

    /*public static java.util.Timer getTime (TimePicker tp){

        int hora = tp.getHour();
        int minuto = tp.getMinute();

        return getTime();
    }*/
}
