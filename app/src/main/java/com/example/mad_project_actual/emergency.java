package com.example.mad_project_actual;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class emergency extends AppCompatActivity {
EditText email,tim;
Button btn;
String emergency,time1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        email=findViewById(R.id.EmailAddress);
        tim=findViewById(R.id.time);
        btn=findViewById(R.id.letgo);
        Intent intent = getIntent();
        String sen_mail = intent.getStringExtra("sender_mail");
        String sender_pass = intent.getStringExtra("sender_password");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emergency=email.getEditableText().toString();
                time1=tim.getEditableText().toString();
                if(emergency.length()!=0 && time1.length()!=0){
                Intent intent=new Intent(getApplicationContext(),dashboard.class);
                email.setText("");
                tim.setText("");
                intent.putExtra("receiver_mail",emergency);
                intent.putExtra("time",time1);
                intent.putExtra("send_mail",sen_mail);
                intent.putExtra("send_pass",sender_pass);

                Log.d(TAG, "EMERGENCY DONE: " + sen_mail + " " );
                startActivity(intent);
//                    new dashboard();
                }
                else
                {
                    Toast.makeText(emergency.this, "Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}