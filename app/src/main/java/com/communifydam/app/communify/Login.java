package com.communifydam.app.communify;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by 2912 on 16/01/2018.
 */

public class Login extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        EditText email = (EditText) findViewById(R.id.emailLogin);
        EditText pwd = (EditText) findViewById(R.id.pwdLogin);
        email.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        pwd.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
