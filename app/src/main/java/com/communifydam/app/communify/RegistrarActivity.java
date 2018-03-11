package com.communifydam.app.communify;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrarActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        mAuth = FirebaseAuth.getInstance();

        Button btn = findViewById(R.id.btn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText email = findViewById(R.id.username);
                EditText pwd = findViewById(R.id.password);
                EditText pwd2 = findViewById(R.id.password2);
                if(pwd.getText().toString().equals(pwd2.getText().toString())) {
                    email.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
                    pwd.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
                    final String val_e = email.getText().toString().trim();
                    final String password = pwd.getText().toString().trim();
                    mAuth.createUserWithEmailAndPassword(val_e, password)
                            .addOnCompleteListener(RegistrarActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(RegistrarActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(RegistrarActivity.this, "Authentication failed." + task.getException(),
                                                Toast.LENGTH_SHORT).show();
                                    } else {

                                        Intent intent = new Intent(RegistrarActivity.this, LoginActivity.class);
                                        intent.putExtra("u", val_e);
                                        intent.putExtra("p", password);
                                        startActivity(intent);
                                        finish();

                                    }
                                }
                            });
                } else {
                   tostar(getString(R.string.passwords_doesnt_match));

                }
            }
        });
    }
    private  void tostar(String texto) {
        Toast t = new Toast(getApplicationContext());

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout,
                (ViewGroup) findViewById(R.id.lytLayout));

        TextView txtMsg = (TextView)layout.findViewById(R.id.txtMensaje);
        txtMsg.setText(texto);

        t.setDuration(Toast.LENGTH_LONG);
        t.setView(layout);
        t.show();
    }
}
