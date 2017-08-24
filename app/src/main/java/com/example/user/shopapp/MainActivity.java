package com.example.user.shopapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    private static final String TAG = "EmailPassword";

    private FirebaseAuth mAuth;

    Button login,signUp;
    EditText userName,password;

    private TextView mStatusTextView;
    private TextView mDetailTextView;

    FirebaseUser currentUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        login = (Button)findViewById(R.id.btn_login);
        signUp = (Button)findViewById(R.id.btn_signup);

        mStatusTextView = (TextView) findViewById(R.id.status);
        mDetailTextView = (TextView) findViewById(R.id.detail);

        userName = (EditText)findViewById(R.id.txtUserName);
        password = (EditText)findViewById(R.id.txt_password);

        mAuth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentRegistration=new Intent(getApplicationContext(),Registration.class);
                startActivity(intentRegistration);

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signIn(userName.getText().toString().trim(),password.getText().toString().trim());



                if (currentUser != null) {
                    Intent intentRegistration=new Intent(getApplicationContext(),DisplayActivity.class);
                    startActivity(intentRegistration);
                    // User is signed in
                } else {

                    mStatusTextView.setText("Username or password incorect!!");
                    //Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
                    // No user is signed in
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();



        //updateUI(currentUser);
    }



    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
       /* if (!validateForm()) {
            return;
        }*/

        //showProgressDialog();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            currentUser = mAuth.getCurrentUser();
                            // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            /*currentUser=null;*/
                           /* Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();*/
                            //updateUI(null);
                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            //mStatusTextView.setText("auth failed");
                        }
                        //hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }
}
