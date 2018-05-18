package com.example.joselarrubiaelich.dbtesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    final String TAG = "TESTING";
    String signInEmail = null;
    String finalEmail = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        finalEmail = FirebaseAuth.getInstance().getUid();
    }

    public void writeDB(View v) {
        String token = null;
        token = FirebaseInstanceId.getInstance().getToken();
        String ID = finalEmail;
        User user = new User(ID, token);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User");
        ref.child(ID).setValue(user.getToken());
        Toast.makeText(getApplicationContext(), ID + " con Toke " + token + "Introducido", Toast.LENGTH_LONG);
    }

    public void readDB(View v) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User");
        ref.child(finalEmail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String token = dataSnapshot.getValue(String.class);
                Toast.makeText(getApplicationContext(),token,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
