package com.communifydam.app.communify;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by 2912 on 06/02/2018.
 */

public class FragmentAddAnuncio_1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_anuncio_step_1, container, false);

        TextView tv = (TextView) v.findViewById(R.id.tvf1);
        tv.setText("Pepito");

        return v;
    }
}

