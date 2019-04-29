package com.example.firebase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firestore.v1beta1.DocumentTransform;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MyProfile extends Fragment {
    private FloatingActionButton send;
    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter adapter = null;
 SharedPreferenes sharedPreferenes;
 Button profile;

    FirebaseFirestore firestore;
    FirebaseApp firebaseApp;
    CollectionReference collectionReference;
    DocumentReference documentReference;
    FirestoreRecyclerOptions<Messages> options;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myprofile, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferenes=new SharedPreferenes(getActivity());
        firebaseApp = FirebaseApp.initializeApp(getActivity());
        myRecyclerView = view.findViewById(R.id.myRecycler);
        firestore = FirebaseFirestore.getInstance();
        send = view.findViewById(R.id.send);
        profile=view.findViewById(R.id.profile);






gotoProfile();
        sendMessage();
       getData();

setUpRecyclerView();

    }

    public void setUpRecyclerView() {



     collectionReference = firestore.collection("USERS/'" + sharedPreferenes.ReadUserName() + "'/Messages");

        Query query = collectionReference.orderBy("time", Query.Direction.DESCENDING);

         options = new FirestoreRecyclerOptions.Builder<Messages>().setQuery(query,Messages.class)
                .build();


        adapter = new RecyclerAdapter(options);

        layoutManager = new LinearLayoutManager(getActivity());
        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setAdapter(adapter);


    }






    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    /* public void getData() {
        all.clear();
        newall.clear();
        String username = "samad97";
        collectionReference = firestore.collection("USERS/'" + username + "'/Messages");

        collectionReference.orderBy("timestamp", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            String mess = "";

            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                    Messages messages = snapshot.toObject(Messages.class);
                    mess = messages.getMessages();
                    MESS messs = new MESS(mess);
                    all.add(messs);

                }
                for (MESS mess : all) {

                    mess.getNewMessage();
                    newall.add(mess);

                }
                adapter.notifyDataSetChanged();
                System.out.println(newall.size());
            }
        });

    }*/


    public void sendMessage() {

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((INavigation)getActivity()).goToProfiel(view,sharedPreferenes.ReadUserName());
            }
        });
    }
public void gotoProfile(){

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((INavigation)getActivity()).gotoMYpProf(view);
            }
        });
}

public void getData(){

    documentReference=firestore.document("USERS/'"+sharedPreferenes.ReadUserName()+"'");
    documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
        @Override
        public void onSuccess(DocumentSnapshot documentSnapshot) {
            if(documentSnapshot.exists()){

                USERS users=documentSnapshot.toObject(USERS.class);
                String name= users.getFullname();
                String info=users.getDescription();
                sharedPreferenes.WriteUserFullName(name);
                sharedPreferenes.WriteUserInfo(info);
                sharedPreferenes.WriteImageUrl(users.getImageUrl());
            }
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {

        }
    });

}

}




