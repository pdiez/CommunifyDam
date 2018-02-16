package com.communifydam.app.communify;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Thread timer= new Thread()
        {
            public void run()
            {
                try
                {
                    //Display for 3 seconds
                    sleep(5000);
                }
                catch (InterruptedException e)
                {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                finally
                {
                    //Goes to Activity  StartingPoint.java(STARTINGPOINT)
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    // close splash activity
                    finish();
                }
            }
        };


        ((AnimatedVectorDrawable) getWindow().getDecorView().getBackground()).start();
        timer.start();

    }

    @Override
    public void onStart() {
        super.onStart();

    }


}