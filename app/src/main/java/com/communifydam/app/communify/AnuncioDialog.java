package com.communifydam.app.communify;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;


/**
 * Created by 2912 on 07/02/2018.
 */


public class AnuncioDialog extends DialogFragment {
    private int paso = 0;
    private ArrayList<String> lista_comunidades;//Para el Fragment2

    AnuncioDialog myDialog;

    public AnuncioDialog() {
        //lista_comunidades=b.getStringArrayList("comunidades");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        lista_comunidades=getArguments().getStringArrayList("comunidades");

        View v = inflater.inflate(R.layout.anuncio_add, container);
        final ViewPager vp = (ViewPager) v.findViewById(R.id.dgPager);
        vp.setAdapter(new AdaptadorPagerAddAnuncio(getChildFragmentManager(),lista_comunidades));

        paso = 0;

        final Button next = (Button) v.findViewById(R.id.bNext);
        final Button prev = (Button) v.findViewById(R.id.bPrev);


        if (paso==0) {
            prev.setEnabled(false);
            next.setEnabled(true);
        } else if(paso==3) {   //cambiado a 0.1.2.3
            prev.setEnabled(true);
            next.setEnabled(true);
        } else {
            prev.setEnabled(true);
            next.setEnabled(true);
        }


        vp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int pos) {
                paso = pos;
                if (paso==0) {
                    prev.setEnabled(false);
                    next.setEnabled(true);
                } else if(paso==3) {
                    prev.setEnabled(true);
                    next.setEnabled(true);
                    next.setText("Finalizar");
                } else {
                    prev.setEnabled(true);
                    next.setEnabled(true);
                }

            }
        });



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(paso==3) {
                    dismiss();
                }

                paso++;
                vp.setCurrentItem(paso);


            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paso--;
                vp.setCurrentItem(paso);

            }
        });

        return v;

    }

    public void onDismiss(DialogInterface dialog)
    {
        Activity activity = getActivity();
        if(activity instanceof DialogCloseListener)
            ((DialogCloseListener)activity).handleDialogClose(dialog);
    }
}
