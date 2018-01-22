package com.communifydam.app.communify;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by 2912 on 16/01/2018.
 */

public class LoginActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        Button btnLogin = (Button) findViewById(R.id.btn);
        EditText email = (EditText) findViewById(R.id.username);
        EditText pwd = (EditText) findViewById(R.id.password);
        email.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        pwd.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        YoYo.with(Techniques.Landing)
                .duration(1900)
                .repeat(0)
                .playOn(findViewById(R.id.contenedor));

    }
}
