package com.example.firebase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registration extends Fragment {
EditText email;
EditText username;
EditText password;
EditText description;
EditText fullName;
Button register;

FirebaseFirestore firestore;
FirebaseApp firebaseApp;
DocumentReference documentReference;

private String emailText;
private String userNameText;
private String passText;
private String descriptionText;
private String fullNameText;
private String imageUrl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.register,container,false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email=view.findViewById(R.id.Email);
        username=view.findViewById(R.id.userName);
        password=view.findViewById(R.id.Password);
        description=view.findViewById(R.id.Description);
        fullName=view.findViewById(R.id.FullName);
        register=view.findViewById(R.id.Register);
        firebaseApp=FirebaseApp.initializeApp(getActivity());

        firestore  =FirebaseFirestore.getInstance();
        Register();
    }



    public void Register(){


register.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(final View view) {

        emailText=email.getText().toString();
        userNameText=username.getText().toString();
        passText=password.getText().toString();
        descriptionText=description.getText().toString();
        fullNameText=fullName.getText().toString();
        imageUrl="";

        USERS users=new USERS(emailText,userNameText,passText,descriptionText,fullNameText,imageUrl);
        documentReference=firestore.document("USERS/'"+userNameText+"'");

      /*  Map<String,Object> map=new HashMap<>();
        map.put("Email",emailText);
        map.put("UserName",userNameText);
        map.put("Password",passText);
        map.put("Description",descriptionText);*/

        documentReference.set(users).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getActivity(), "User Registered", Toast.LENGTH_SHORT).show();
                ((INavigation)getActivity()).goToLogin(view);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Registration Failed", Toast.LENGTH_SHORT).show();
            }
        });


    }
});


    }


}
