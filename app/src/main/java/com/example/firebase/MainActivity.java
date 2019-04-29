package com.example.firebase;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import io.opencensus.tags.Tag;

public class MainActivity extends AppCompatActivity implements INavigation {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

       // login1();
       // loadData();
     //   update();
    }

    @Override
    public void goToRegister(View view) {
        Navigation.findNavController(view).navigate(R.id.action_login2_to_registration);
    }

    @Override
    public void goToLogin(View view) {
        Navigation.findNavController(view).navigate(R.id.action_registration_to_login22);
    }

    @Override
    public void goToProfiel(View view,String username) {
        Bundle bundle=new Bundle();
        bundle.putString("you",username);
        Navigation.findNavController(view).navigate(R.id.action_myProfile_to_profile,bundle);

    }

    @Override
    public void gobacktoLogin(View view) {
        Navigation.findNavController(view).navigate(R.id.action_myProf_to_login2);
    }

    @Override
    public void gotoMYprofile(View view,String username) {
        Bundle bundle=new Bundle();
        bundle.putString("you",username);
        Navigation.findNavController(view).navigate(R.id.action_login2_to_myProfile,bundle);
    }

    @Override
    public void gobacktoMYprofile(View view,String username) {
        Bundle bundle=new Bundle();
        bundle.putString("you",username);
        Navigation.findNavController(view).navigate(R.id.action_profile_to_myProfile2,bundle);
    }

    @Override
    public void gotoMYpProf(View view) {
        Navigation.findNavController(view).navigate(R.id.action_myProfile_to_myProf);
    }




/* public void login1(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             user   =username.getText().toString();
             pass=password.getText().toString();
                documentReference=firestore.document("USERS/'"+user+"'");

                documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if(documentSnapshot.exists()){

                            user=documentSnapshot.getString("user");
                             pass=documentSnapshot.getString("pass");
                            Toast.makeText(MainActivity.this, "login succesful", Toast.LENGTH_SHORT).show();
                            showData.setText("user :'"+user+"' \n pass: '"+pass+"'");
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
*/

   /* public void login(){

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user=username.getText().toString();
                String pass=password.getText().toString();
                documentReference=firestore.document("USERS/'"+user+"'");
                Map<String,Object> map=new HashMap<>();
                map.put(KEY_USER,user);
                map.put(KEY_PASS,pass);

                documentReference.set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "User Entered", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Process Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    */


  /*  @Override
    protected void onStart() {
        super.onStart();
       documentReference.addSnapshotListener(this,new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
               if(e!=null){
                   Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                   Log.d("TAG",e.toString());
                   return;
               }

                if(documentSnapshot.exists()){

                    String user=documentSnapshot.getString("user");
                    String pass=documentSnapshot.getString("pass");

                    showData.setText("user :'"+user+"' \n pass: '"+pass+"'");
                }
            }
        });
    }

    public void update(){

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass=password.getText().toString();
                Map<String,Object> map=new HashMap<>();
                map.put(KEY_PASS,pass);

                documentReference.update(map);

            }
        });
    }





   public void loadData(){

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if(documentSnapshot.exists()){

                            String user=documentSnapshot.getString("user");
                            String pass=documentSnapshot.getString("pass");

                            showData.setText("user :'"+user+"' \n pass: '"+pass+"'");
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        });
   }*/


    public String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    }
