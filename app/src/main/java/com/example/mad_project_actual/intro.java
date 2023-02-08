package com.example.mad_project_actual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class intro extends AppCompatActivity {
ImageView bg,logo;
LottieAnimationView lottie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);
        logo=findViewById(R.id.imageView15);
        bg=findViewById(R.id.imageView14);
        lottie=findViewById(R.id.lottie);
        bg.animate().translationY(-2000).setDuration(1000).setStartDelay(4100);
        logo.animate().translationY(2000).setDuration(1000).setStartDelay(4000);
        lottie.animate().translationY(2000).setDuration(1000).setStartDelay(4000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gotoactivitymain();
            }
        }, 2600);
    }
    public void gotoactivitymain()
    {
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}