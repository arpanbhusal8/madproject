package com.example.mad_project_actual;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabl;
    ViewPager vpger;
    float v=0;
    FloatingActionButton google,instagram,twitter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabl=findViewById(R.id.tablayout);
        vpger=findViewById(R.id.viewPager2);
        google=findViewById(R.id.gogle);
        twitter=findViewById(R.id.twter);
        instagram=findViewById(R.id.insta);

        tabl.addTab(tabl.newTab().setText("Login"));
        tabl.addTab(tabl.newTab().setText("Sign Up"));
        tabl.setTabGravity(tabl.GRAVITY_FILL);

    final loginadpt adpt=new loginadpt(getSupportFragmentManager(),this,tabl.getTabCount());

    vpger.setAdapter(adpt);
    vpger.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabl));


        google.setTranslationY(300);
        instagram.setTranslationY(300);
        twitter.setTranslationY(300);
        tabl.setTranslationY(300);

        google.setAlpha(v);
        instagram.setAlpha(v);
        twitter.setAlpha(v);
        tabl.setAlpha(v);

        google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        instagram.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        twitter.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        tabl.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();


    }
}