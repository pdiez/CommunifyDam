package com.communifydam.app.communify;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

/**
 * Created by GABBERCORE on 10/02/2018.
 */

public class FragmentAddAnuncio_5 extends Fragment{

    private RadioButton a, b, c, d, e, f;
    private TextView tv_hora;
    private ListView lv_iconos;
    private Button bt_crearAnuncio;
    private RadioGroup iconos;

    private GrabarAnuncio ga;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_anuncio_step_4, container, false);

        bt_crearAnuncio = (Button)v.findViewById(R.id.bt_crear_anuncio);
        tv_hora = (TextView) v.findViewById(R.id.text_selec_hora);
        a = (RadioButton) v.findViewById(R.id.ib_a); // brocha
        b = (RadioButton) v.findViewById(R.id.ib_b); // pets
        c = (RadioButton) v.findViewById(R.id.ib_c); // wifi
        d = (RadioButton) v.findViewById(R.id.ib_d); // cart
        e = (RadioButton) v.findViewById(R.id.ib_e); // salud
        f = (RadioButton) v.findViewById(R.id.ib_f); // garden
        iconos = (RadioGroup) v.findViewById(R.id.rg_group_icon);

        ViewPager vp=(ViewPager) this.getParentFragment().getView().findViewById(R.id.dgPager);
        AdaptadorPagerAddAnuncio mi_vp=(AdaptadorPagerAddAnuncio)vp.getAdapter();

        mi_vp.setA(a);
        mi_vp.setB(b);
        mi_vp.setC(c);
        mi_vp.setD(d);
        mi_vp.setE(e);
        mi_vp.setF(f);

        Log.v("check_log","Ofrecer -> " + mi_vp.getOfrecer().isChecked());

        mi_vp.setBt_crearAnuncio(bt_crearAnuncio);


        ga=(GrabarAnuncio)mi_vp;//Está por ver
        bt_crearAnuncio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ga.grabar("SinSeleccion");
            }
        });


        return v;
    }


}
