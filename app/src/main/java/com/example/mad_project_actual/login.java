package com.example.mad_project_actual;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends Fragment {
EditText email,pass;
TextView forget;
Button login;
FirebaseDatabase rootNode;
DatabaseReference dbrf;
float v=0;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,  Bundle savedInstanceState)
    {
        ViewGroup root =(ViewGroup) inflater.inflate(R.layout.login,container,false);

       email = root.findViewById(R.id.loginemail);
       pass=root.findViewById(R.id.loginpw);
       login=root.findViewById(R.id.login);
       forget=root.findViewById(R.id.forget);


       email.setTranslationX(800);
       pass.setTranslationX(800);
       forget.setTranslationX(800);
       login.setTranslationX(800);

       email.setAlpha(v);
       pass.setAlpha(v);
       login.setAlpha(v);
       forget.setAlpha(v);

       email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
       pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
       login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
       forget.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();



       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String mail=email.getEditableText().toString();
               String pw=pass.getEditableText().toString();
               rootNode=FirebaseDatabase.getInstance();
               dbrf=rootNode.getReference("users");
               Query check=dbrf.orderByChild("email").equalTo(mail);
               check.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       if(snapshot.exists())
                       {

                           String pwfromdb=snapshot.child(mail).child("password").getValue(String.class);
                           System.out.println(pwfromdb);
                           System.out.println(pw);

                           if(pwfromdb.equals(pw))
                           {
                               Intent intent= new Intent(getContext(),emergency.class);
                               intent.putExtra("sender_mail",mail);
                               intent.putExtra("sender_password",pw);
                               email.setText("");
                               pass.setText("");
                               startActivity(intent);

                           }
                           else
                           {
                               pass.setError("wrong password");
                               email.setText("");
                               pass.setText("");
                               pass.requestFocus();
                           }
                       }
                       else
                       {
                           Toast.makeText(getContext(), "your login credentials are wrong", Toast.LENGTH_SHORT).show();
//                           System.out.println(pwfromdb);
//                           System.out.println(pw);

                       }
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {
                   }
               });

           }
       });

        return root;
    }
}
