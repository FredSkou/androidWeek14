package com.example.androidweek14;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;



    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
    public void createUser(View view){

        EditText emailField = findViewById(R.id.emailTextField);
        EditText passwordField = findViewById(R.id.passwordTextField);

        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        final Intent loggedinIntent = new Intent(this,loggedin.class);



        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.i("LoginTest","createUserWithemail: Success!");
                    FirebaseUser user = mAuth.getCurrentUser();
                    startActivity(loggedinIntent);

                } else{
                    Log.i("LoginTest","createUserWithemail: FAIL!"+task.getException());
                }
            }
        });
    }
    public void login(View view){

        EditText emailField = findViewById(R.id.emailTextField);
        EditText passwordField = findViewById(R.id.passwordTextField);

        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        final Intent loggedinIntent = new Intent(this,loggedin.class);




        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    startActivity(loggedinIntent);
                    Log.i("LoginTest","Login Success!"+user.getEmail());
                } else {
                    Log.i("LoginTest","Login Failure!"+task.getException());
                }
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

    }
}
