package com.communifydam.app.communify;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by GABBERCORE on 12/02/2018.
 */

public class FragmentAddAnuncio_6 extends Fragment {


    private ListView lv_com;
    private Button bt_crearAnuncio;

    private GrabarAnuncio ga;
    /*private InsercionAnuncio ia;

     public interface InsercionAnuncio
     {
         //Argumentos que esten en este Fragment
         public void grabarAnuncio();
     }*/
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_anuncio_step_5, container, false);


        bt_crearAnuncio = (Button)v.findViewById(R.id.bt_crear_anuncio);


        ViewPager vp=(ViewPager) this.getParentFragment().getView().findViewById(R.id.dgPager);
        AdaptadorPagerAddAnuncio mi_vp=(AdaptadorPagerAddAnuncio)vp.getAdapter();
        mi_vp.setLv_com(lv_com);
        mi_vp.setBt_crearAnuncio(bt_crearAnuncio);

        ga=(GrabarAnuncio)mi_vp;//Está por ver
        bt_crearAnuncio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lv_com.getSelectedItem()==null)
                {ga.grabar("SinSeleccion");}
                else
                {
                    ga.grabar(lv_com.getSelectedItem().toString());
                }

            }
        });
        return v;
    }



}
