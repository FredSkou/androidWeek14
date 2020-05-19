package com.example.androidweek14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loggedin extends AppCompatActivity {

    private FirebaseAuth mAuth;

    public void signOut(View view){
        FirebaseAuth.getInstance().signOut();
        Intent signout = new Intent(this,MainActivity.class);
        startActivity(signout);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin);

        TextView userInfoField = findViewById(R.id.userInfoTextField);


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        userInfoField.setText(user.getEmail());
    }
}
