package com.communifydam.app.communify;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Calendar;

/**
 * Created by GABBERCORE on 10/02/2018.
 */

public class FragmentAddAnuncio_3 extends Fragment {

    private DatePicker date;
    private TextView text_date;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_anuncio_step_3, container, false);


        date = (DatePicker)v.findViewById(R.id.selector_fecha);
        text_date = (TextView) v.findViewById(R.id.text_selec_fecha);

        ViewPager vp=(ViewPager) this.getParentFragment().getView().findViewById(R.id.dgPager);
        AdaptadorPagerAddAnuncio mi_vp=(AdaptadorPagerAddAnuncio)vp.getAdapter();
        mi_vp.setDp(date);



        return v;
    }

    public static java.util.Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }
}
