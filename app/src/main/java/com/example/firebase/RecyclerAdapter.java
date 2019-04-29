package com.example.firebase;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class RecyclerAdapter extends FirestoreRecyclerAdapter<Messages,RecyclerAdapter.MyViewHolder> {



    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options

     */
    public RecyclerAdapter(@NonNull FirestoreRecyclerOptions<Messages> options) {

        super(options);


    }



    @Override
    protected void onBindViewHolder(@NonNull final MyViewHolder holder, int position, @NonNull final Messages model) {
holder.messText.setText(model.getMessages());



    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_layout, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
TextView messText;

        public MyViewHolder(View itemView) {
            super(itemView);
messText=itemView.findViewById(R.id.messageText);


        }
    }
}