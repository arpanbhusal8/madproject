package com.example.mad_project_actual;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends Fragment {
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    EditText mail,password,confirmpw,phno;
    Button btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root =(ViewGroup) inflater.inflate(R.layout.signup,container,false);

        mail=root.findViewById(R.id.signupemail);
        password=root.findViewById(R.id.pw);
        confirmpw=root.findViewById(R.id.confirmpw);
        btn=root.findViewById(R.id.signup);
        phno=root.findViewById(R.id.phno);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mail.getEditableText().toString();
                String pass = password.getEditableText().toString();
                String conpass = confirmpw.getEditableText().toString();
                String phn = phno.getEditableText().toString();
                if(pass.equals(conpass) && email.length()!=0 &&  phn.length()!=0 && pass.length()!=0){
                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("users");
                    helper helpforsignup = new helper(email, pass, phn);
                    reference.child(email).setValue(helpforsignup);
                    mail.setText("");
                    password.setText("");
                    confirmpw.setText("");
                    phno.setText("");
                    Toast.makeText(getContext(), "Database has been Updated..Please slide to login page and login", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), "PLEASE MATCH YOUR PASSWORDS", Toast.LENGTH_SHORT).show();
                }


            }
        });
        return root;
    }
}
