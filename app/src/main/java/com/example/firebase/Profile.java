package com.example.firebase;

import android.media.tv.TvInputManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firestore.v1beta1.DocumentTransform;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Profile extends Fragment {

SharedPreferenes sharedPreferenes;
Button get;
TextView showinfo;
EditText findUsername;
EditText sendMessage;
    FirebaseFirestore firestore;
    FirebaseApp firebaseApp;
    CollectionReference collectionReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.profile,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferenes=new SharedPreferenes(getActivity());
        firebaseApp=FirebaseApp.initializeApp(getActivity());

        firestore  =FirebaseFirestore.getInstance();


        get=view.findViewById(R.id.getObject);

        findUsername=view.findViewById(R.id.findUsers);
        sendMessage=view.findViewById(R.id.messages);
        sharedPreferenes=new SharedPreferenes(getActivity());



        showInfo();
    }

    public void showInfo()
    {

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                String username=findUsername.getText().toString();
                String message = sendMessage.getText().toString();


                if(username.isEmpty() || message.isEmpty()){
                    Toast.makeText(getActivity(), "PLEASE ENTER THE Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    collectionReference = firestore.collection("USERS/'" + username + "'/Messages");

                    Messages messages = new Messages(message);

                    collectionReference.add(messages).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {


                            Toast.makeText(getActivity(), "Message send succesfully", Toast.LENGTH_SHORT).show();
                            ((INavigation) getActivity()).gobacktoMYprofile(view, sharedPreferenes.ReadUserName());
                        }
                    });

                }

            }
        });
    }
}
