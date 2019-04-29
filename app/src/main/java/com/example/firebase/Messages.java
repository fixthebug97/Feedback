package com.example.firebase;


import com.google.firebase.Timestamp;
import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.ServerTimestamp;
import com.google.firebase.firestore.model.value.ServerTimestampValue;
import com.google.firestore.v1beta1.DocumentTransform;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Messages implements Serializable {

    String messages;


    @ServerTimestamp Timestamp time;



    public Messages(){}

    public Messages(String messages) {
        this.messages = messages;

        this.time = Timestamp.now();

    }

    public Timestamp getTime() {
        return time;
    }

    public String getMessages() {
        return messages;
    }

}
