package com.communifydam.app.communify;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


/**
 * Created by 2912 on 06/02/2018.
 * Modified by Ruben on 19/02/2018
 */

public class AdaptadorPagerAddAnuncio extends FragmentPagerAdapter implements GrabarAnuncio {

    private Anuncio anuncio;

    private EditText edit_titulo;
    private EditText edit_descripcion;
    private RadioGroup group;
    private RadioButton ofrecer;
    private RadioButton buscar;
    private DatePicker dp;
    private TimePicker tp;
    private ListView lv_com;
    private Button bt_crearAnuncio;

    private String date;
    private String hora;

    private static int day ;
    private static int month ;
    private static int year;

    private static int minute;
    private static int hour;

    public void setTp(TimePicker tp) {
        this.tp = tp;
    }

    public void setLv_com(ListView lv_com) {
        this.lv_com = lv_com;
    }

    public void setBt_crearAnuncio(Button bt_crearAnuncio) {
        this.bt_crearAnuncio = bt_crearAnuncio;
    }

    public void setDp(DatePicker dp) {
        this.dp = dp;
    }

    public void setEdit_titulo(EditText edit_titulo) {
        this.edit_titulo = edit_titulo;

    }

    public void setEdit_descripcion(EditText edit_descripcion) {
        this.edit_descripcion = edit_descripcion;

    }

    public void setGroup(RadioGroup group) {
        this.group = group;

    }

    public void setOfrecer(RadioButton ofrecer) {
        this.ofrecer = ofrecer;

    }

    public void setBuscar(RadioButton buscar) {
        this.buscar = buscar;

    }

    private Calendar cal = Calendar.getInstance();



    public AdaptadorPagerAddAnuncio(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {

        return 5;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment f;
        switch (position) {

            case 0:
                f = new FragmentAddAnuncio_1();

                return f;
            case 1:
                f = new FragmentAddAnuncio_2();

                return f;
            case 2:
                f = new FragmentAddAnuncio_3();

                return f;
            case 3:
                f = new FragmentAddAnuncio_5();

                return f;
            case 4:
                f = new FragmentAddAnuncio_6();

                boolean necesitar_chekeado = buscar.isChecked();
                boolean ofrecer_chekeado= ofrecer.isChecked();

                Log.v("adaptador", edit_titulo.getText().toString());
                Log.v("adaptador", edit_descripcion.getText().toString());
                Log.v("adaptador", getDateFromDatePicker(dp).toString());

                return f;

            default:
                return new FragmentAddAnuncio_1();
        }
    }

    public void onRadioButtonClicked(View f) {

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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void grabar(String valor_list_view) {

        boolean ofrec = ofrecer.isChecked();
        boolean busc = buscar.isChecked();

        String edit_tit = edit_titulo.getText().toString();
        String edit_descr = edit_descripcion.getText().toString();

        day=dp.getDayOfMonth();
        month=dp.getMonth();
        year=dp.getYear();
        cal = Calendar.getInstance();
        date = day + " " + month + " " +year;


        hour = tp.getHour();
        minute = tp.getMinute();
        hora = hour + " " + minute;

        Log.v("METODO GRABAR", "ENTRA A GRABAR");

    }

    public static java.util.Date getDateFromDatePicker(DatePicker datePicker){
         day = datePicker.getDayOfMonth();
         month = datePicker.getMonth();
         year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }
}

