package com.communifydam.app.communify;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by 2912 on 06/02/2018.
 * Modified by Ruben on 8/02/2018.
 */

public class FragmentAddAnuncio_1 extends Fragment {

    private RadioGroup group;
    private RadioButton ofrecer ;
    private RadioButton buscar ;
    TextView tv ;
    ViewPager vp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_anuncio_step_1, container, false);

        group = (RadioGroup)v.findViewById(R.id.grupo);
        ofrecer = (RadioButton)v.findViewById(R.id.radioOfrecer);
        buscar = (RadioButton)v.findViewById(R.id.radioBuscar);
        tv = (TextView) v.findViewById(R.id.tvf1);
       /* */

        vp=(ViewPager) this.getParentFragment().getView().findViewById(R.id.dgPager);
        AdaptadorPagerAddAnuncio mi_vp=(AdaptadorPagerAddAnuncio)vp.getAdapter();
        mi_vp.setGroup(group);
        mi_vp.setBuscar(buscar);
        mi_vp.setOfrecer(ofrecer);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    public void onRadioButtonClicked(View v) {

        int checked = group.getCheckedRadioButtonId();

        switch (checked) {
            case R.id.radioOfrecer:
                if (ofrecer.isChecked())
                    // Crea anuncio con OFRECER
                    break;
            case R.id.radioBuscar:
                if (buscar.isChecked())
                    // Crea anuncio con BUSCAR
                    break;

        }


    }
}

