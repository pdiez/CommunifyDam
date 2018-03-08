package com.communifydam.app.communify;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Created by GABBERCORE on 10/02/2018.
 */

public class FragmentAddAnuncio_5 extends Fragment{

    private ImageButton a, b, c, d, e, f, prueba;
    private TextView tv_hora;
    private ListView lv_iconos;
    private Button bt_crearAnuncio;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_anuncio_step_4, container, false);

        bt_crearAnuncio = (Button)v.findViewById(R.id.bt_crear_anuncio);
        tv_hora = (TextView) v.findViewById(R.id.text_selec_hora);
        a = (ImageButton) v.findViewById(R.id.ib_a);
        b = (ImageButton) v.findViewById(R.id.ib_b);
        c = (ImageButton) v.findViewById(R.id.ib_c);
        d = (ImageButton) v.findViewById(R.id.ib_d);
        e = (ImageButton) v.findViewById(R.id.ib_e);
        f = (ImageButton) v.findViewById(R.id.ib_f);

        ViewPager vp=(ViewPager) this.getParentFragment().getView().findViewById(R.id.dgPager);
        AdaptadorPagerAddAnuncio mi_vp=(AdaptadorPagerAddAnuncio)vp.getAdapter();

        mi_vp.setBt_crearAnuncio(bt_crearAnuncio);
        

        escoger_icono();

        return v;
    }

    private void escoger_icono() {

        final Boolean clicked = new Boolean(false);

        a.setTag(clicked);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((Boolean) a.getTag()) == false) {
                    a.setImageResource(R.drawable.ic_healthcare);
                    a.setTag(new Boolean(true));
                    a.setColorFilter(Color.argb(180, 255, 255, 255));
                    Toast.makeText(getActivity(), "HEALTHCARE SELECTED",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        b.setTag(clicked);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((Boolean) b.getTag()) == false) {
                    b.setImageResource(R.drawable.ic_paintbrush);
                    b.setTag(new Boolean(true));
                    b.setColorFilter(Color.argb(180, 255, 255, 255));
                    Toast.makeText(getActivity(), "PAINTBRUSH SELECTED",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        c.setTag(clicked);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((Boolean) c.getTag()) == false) {
                    c.setImageResource(R.drawable.ic_garden);
                    c.setTag(new Boolean(true));
                    c.setColorFilter(Color.argb(180, 255, 255, 255));
                    Toast.makeText(getActivity(), "FLOWER SELECTED",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        d.setTag(clicked);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((Boolean) d.getTag()) == false) {
                    d.setImageResource(R.drawable.ic_shopping);
                    d.setTag(new Boolean(true));
                    d.setColorFilter(Color.argb(180, 255, 255, 255));
                    Toast.makeText(getActivity(), "SHOPPING CART SELECTED",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        e.setTag(clicked);
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((Boolean) e.getTag()) == false) {
                    e.setImageResource(R.drawable.ic_wifi);
                    e.setTag(new Boolean(true));
                    e.setColorFilter(Color.argb(180, 255, 255, 255));
                    Toast.makeText(getActivity(), "WIFI SELECTED",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        f.setTag(clicked);
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((Boolean) f.getTag()) == false) {
                    f.setImageResource(R.drawable.ic_pets);
                    f.setTag(new Boolean(true));
                    f.setColorFilter(Color.argb(180, 255, 255, 255));
                    Toast.makeText(getActivity(), "PETS SELECTED",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
