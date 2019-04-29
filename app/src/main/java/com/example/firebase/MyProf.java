package com.example.firebase;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class MyProf extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 1;
    Button signout;
    FloatingActionButton choose;
    Button upload;
    TextView fullName;
    TextView Info;
    CircularImageView dp;
    FirebaseFirestore firestore;
    FirebaseApp firebaseApp;
    DocumentReference documentReference;
    SharedPreferenes sharedPreferenes;
    ProgressBar mProgressBar;
    private Uri mImageUri;
    FirebaseStorage storage;
    private StorageReference mStorageRef;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.myprof,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signout=view.findViewById(R.id.signout);
        choose=view.findViewById(R.id.choose);
        upload=view.findViewById(R.id.upload);
        dp=view.findViewById(R.id.profilePic);
        fullName=view.findViewById(R.id.fullName);
        Info=view.findViewById(R.id.Info);
        mProgressBar = view.findViewById(R.id.progress_bar);
        sharedPreferenes=new SharedPreferenes(getActivity());
        firebaseApp=FirebaseApp.initializeApp(getActivity());
storage=FirebaseStorage.getInstance();
mStorageRef=storage.getReference();
        firestore  =FirebaseFirestore.getInstance();
        documentReference = firestore.document("USERS/'"+sharedPreferenes.ReadUserName()+"'" );
        getInformation();
        signout();
        ChooseFile();
        upload();
        setImage();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK){
                ((View)choose).setVisibility(View.GONE);
            upload.setVisibility(View.VISIBLE);
            mImageUri = data.getData();

            Picasso.get().load(mImageUri).into(dp);
        }
    }


    public void signout(){
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferenes.writeloginstatus(false);
                sharedPreferenes.WriteUserName("");
                ((INavigation)getActivity()).gobacktoLogin(view);
            }
        });

    }

    public void ChooseFile(){

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
openFileChooser();

            }
        });
    }

    public void upload(){

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((View)choose).setVisibility(View.VISIBLE);
upload.setVisibility(View.GONE);
uploadImage();
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    public void getInformation(){


                    String name= sharedPreferenes.ReadUserFullName();
                    String info=sharedPreferenes.ReadUserInfo();
                    fullName.setText(name);
                    Info.setText(info);



    }

    public void uploadImage(){

        if (mImageUri != null) {
mProgressBar.setVisibility(View.VISIBLE);
            final StorageReference fileReference = mStorageRef.child("images/"+ UUID.randomUUID().toString());
         fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
             @Override
             public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // Upload upload=new Upload(taskSnapshot.getUploadSessionUri().toString());

                     Handler handler = new Handler();
                     handler.postDelayed(new Runnable() {
                         @Override
                         public void run() {
                             mProgressBar.setProgress(0);
                             mProgressBar.setVisibility(View.GONE);
                         }
                     }, 800);

fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
    @Override
    public void onSuccess(Uri uri) {
        Map<String,Object> map=new HashMap<>();
        map.put("imageUrl",uri.toString());

        //  Upload upload=new Upload(taskSnapshot.getUploadSessionUri().toString());
        documentReference.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), ""+e.toString(), Toast.LENGTH_LONG).show();
                System.out.println(e.toString());
            }
        });
    }
});


                 Toast.makeText(getActivity(), ""+sharedPreferenes.ReadUserName(), Toast.LENGTH_SHORT).show();

                 Toast.makeText(getActivity(), "Image Uplaoded", Toast.LENGTH_SHORT).show();
             }
         }).addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                 Toast.makeText(getActivity(), ""+e.toString(), Toast.LENGTH_SHORT).show();
             }
         }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
             @Override
             public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                 double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                 mProgressBar.setProgress((int) progress);
             }
         });

    }

    }

    public void setImage(){
        String url=sharedPreferenes.ReadImageUrl();
if(url.equals("")) {

dp.setBackgroundResource(R.drawable.noimage);
}

else{

        Picasso.get().load(sharedPreferenes.ReadImageUrl()).into(dp);
    }

}





}
