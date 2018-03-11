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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by 2912 on 06/02/2018.
 * Modified by Ruben on 19/02/2018
 */

public class AdaptadorPagerAddAnuncio extends FragmentPagerAdapter implements GrabarAnuncio {

    private Anuncio anuncio;

    public Anuncio getAnuncio() {
        return anuncio;
    }
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    private EditText edit_titulo;
    private EditText edit_descripcion;
    private RadioGroup group,iconos;
    private RadioButton ofrecer,buscar,a,b,c,d,e,f;

    private DatePicker dp;
    private TimePicker tp;
    private ListView lv_com;
    private Button bt_crearAnuncio;
    private Spinner spinner_com;

    private ArrayList<String> lista_comunidades;

    private String date;
    private String hora;

    private static int day ;
    private static int month ;
    private static int year;

    private static int minute;
    private static int hour;

    public void setA(RadioButton a) {
        this.a = a;
    }

    public void setB(RadioButton b) {
        this.b = b;
    }

    public void setC(RadioButton c) {
        this.c = c;
    }

    public void setD(RadioButton d) {
        this.d = d;
    }

    public void setE(RadioButton e) {
        this.e = e;
    }

    public void setF(RadioButton f) {
        this.f = f;
    }

    public void setIconos(RadioGroup iconos) {
        this.iconos = iconos;
    }

    public void setSpinner_com(Spinner spinner_com) {
        this.spinner_com = spinner_com;
    }

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



    public AdaptadorPagerAddAnuncio(FragmentManager fm, ArrayList<String> lista_comunidades) {
        super(fm);
        this.lista_comunidades=lista_comunidades;
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
                Bundle b=new Bundle();
                b.putStringArrayList("comunidades", lista_comunidades);
                f.setArguments(b);
                return f;
            case 2:
                f = new FragmentAddAnuncio_3();

                return f;
            case 3:
                f = new FragmentAddAnuncio_5();

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

        Anuncio anuncio = new Anuncio();

        if(ofrecer.isChecked()) {
            anuncio.setTipo(0);
        } else {
            anuncio.setTipo(1);
        }
        anuncio.setTitulo(edit_titulo.getText().toString());
        anuncio.setDescripcion(edit_descripcion.getText().toString());

        day=dp.getDayOfMonth();
        month=dp.getMonth();
        year=dp.getYear();
        cal = Calendar.getInstance();
        anuncio.setFecha(day + "/" + month + "/" +year);
        anuncio.setImagen("@drawable/ic_home_black_24dp");
        anuncio.setCommunityId("-L5oBRy5-xGvGYKlcFDL");
        anuncio.setUserId(mAuth.getCurrentUser().getUid());

        DatabaseReference dbanuncio = mData.child("anuncios");
        String mkey = dbanuncio.push().getKey();
        dbanuncio.child(mkey).setValue(anuncio);


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

