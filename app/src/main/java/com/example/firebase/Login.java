package com.example.firebase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.google.firebase.firestore.DocumentSnapshot;

import com.google.firebase.firestore.FirebaseFirestore;


public class Login extends Fragment  {
Button login;
Button gotoRegister;
EditText username;
EditText password;
String user;
String pass;

    FirebaseFirestore firestore;
    FirebaseApp firebaseApp;
    DocumentReference documentReference;
SharedPreferenes sharedPreferenes;
   Fragment fragment;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.login, container,false);
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    login=view.findViewById(R.id.login);
    username=view.findViewById(R.id.loginName);
    password=view.findViewById(R.id.loginPass);
fragment=getFragmentManager().findFragmentById(R.id.fragment);

    gotoRegister=view.findViewById(R.id.gotoRegister);
        firebaseApp=FirebaseApp.initializeApp(getActivity());

        firestore  =FirebaseFirestore.getInstance();

sharedPreferenes=new SharedPreferenes(getActivity());

if(sharedPreferenes.Readloginstatus()){

    ((INavigation)getActivity()).gotoMYprofile(view,sharedPreferenes.ReadUserName());
}

    goToRegister();
    Login();

    }



    public void Login(){

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                user   =username.getText().toString();
                pass=password.getText().toString();
                documentReference=firestore.document("USERS/'"+user+"'");

                documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {


                        if(documentSnapshot.exists()){


USERS users=documentSnapshot.toObject(USERS.class);

String u=users.getUsername();
String p=users.getPassword();
if(user.equals(u) && pass.equals(p)){


    Toast.makeText(getActivity(), ""+user, Toast.LENGTH_SHORT).show();
                            Toast.makeText(getActivity(), "login succesful", Toast.LENGTH_SHORT).show();

sharedPreferenes.writeloginstatus(true);
sharedPreferenes.WriteUserName(user);
    ((INavigation)getActivity()).gotoMYprofile(view,sharedPreferenes.ReadUserName());
}

else {

    Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_SHORT).show();
}


                        }
                        


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        });

    }


    public void goToRegister(){
        gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((INavigation)getActivity()).goToRegister(view);
            }
        });

    }
}
