package com.communifydam.app.communify;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by 2912 on 06/02/2018.
 */

public class AdaptadorPagerAddComunidad extends FragmentPagerAdapter implements GrabarAnuncio {



    private EditText et_pin;
    private Anuncio anuncio;
    private EditText et_nombre_comunidad;
    private EditText et_descripcion_comunidad;
    private Button btn_crear_comunidad;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    public void setEt_nombre_comunidad(EditText et_nombre_comunidad) {
        this.et_nombre_comunidad = et_nombre_comunidad;
    }

    public void setEt_descripcion_comunidad(EditText et_descripcion_comunidad) {
        this.et_descripcion_comunidad = et_descripcion_comunidad;
    }

    public EditText getEt_pin() {
        return et_pin;
    }

    public void setEt_pin(EditText et_pin) {
        this.et_pin = et_pin;
    }

    public AdaptadorPagerAddComunidad(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {

        return 2;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment f;
        switch (position) {

            case 0:
                f = new FragmentAddComunidad_1();

                return f;
            case 1:
                f = new FragmentAddComunidad_2();

                return f;

            default:
                return new FragmentAddComunidad_1();
        }
    }

    @Override
    public void grabar(String grabar) {
        Comunidad comunidad = new Comunidad();
        comunidad.setNombre(et_nombre_comunidad.getText().toString());
        comunidad.setOwnerId(mAuth.getCurrentUser().getUid());
        comunidad.setDescripcion(et_descripcion_comunidad.getText().toString());
        comunidad.setPin(et_pin.getText().toString());

        DatabaseReference dbanuncio = mData.child("comunidades");
        String mkey = dbanuncio.push().getKey();
        comunidad.setUid(mkey);
        dbanuncio.child(mkey).setValue(comunidad);

    }


}
