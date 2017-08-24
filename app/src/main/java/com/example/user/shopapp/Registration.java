package com.example.user.shopapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by USER on 8/24/2017.
 */
public class Registration extends AppCompatActivity {
    private static final String TAG = "NewPostActivity";
    private static final String REQUIRED = "Required";

    private DatabaseReference mDatabase;

    EditText fname,lname=null,addr=null,mobile=null,email=null,pwd=null,confirmpwd=null;
    Button signup1;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        // [START initialize_database_ref]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END initialize_database_ref]

        signup1 = (Button)findViewById(R.id.btn_signup1);

        fname = (EditText) findViewById(R.id.txt_firstname);
        lname = (EditText) findViewById(R.id.txt_lastname);
        addr = (EditText) findViewById(R.id.txt_address);
        mobile = (EditText) findViewById(R.id.txt_mobile);
        email = (EditText) findViewById(R.id.txt_email);
        pwd = (EditText) findViewById(R.id.txt_password);
        confirmpwd = (EditText) findViewById(R.id.txt_confirmpassword);

        signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitPost();

            }
        });


    }

    private void submitPost() {
        final String fname1 = fname.getText().toString();
        final String lname1 = lname.getText().toString();
        final String address = addr.getText().toString();
        final String email1 = email.getText().toString();
        final String mobile1 = mobile.getText().toString();
        final String password = pwd.getText().toString();
        final String conformPwd = confirmpwd.getText().toString();

        // fname is required
        if (TextUtils.isEmpty(fname1)) {
            fname.setError(REQUIRED);
            return;
        }

        // lname is required
        if (TextUtils.isEmpty(lname1)) {
            lname.setError(REQUIRED);
            return;
        }

        // address is required
        if (TextUtils.isEmpty(address)) {
            addr.setError(REQUIRED);
            return;
        }

        // email is required
        if (TextUtils.isEmpty(email1)) {
            email.setError(REQUIRED);
            return;
        }

        // mobile is required
        if (TextUtils.isEmpty(mobile1)) {
            mobile.setError(REQUIRED);
            return;
        }

        // passwrod is required
        if (TextUtils.isEmpty(password)) {
            pwd.setError(REQUIRED);
            return;
        }

        // confirm password is required
        if (TextUtils.isEmpty(conformPwd)) {
            confirmpwd.setError(REQUIRED);
            return;
        }

        // password matching
        if (!password.equals(conformPwd)) {
            confirmpwd.setError("Password doesn't match");
            return;
        }

        createAccount(email1, password);

        User user = new User(fname1, lname1, address, email1);

        try {
            mDatabase.child("users").child(mobile1).setValue(user);
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }






    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
       /* if (!validateForm()) {
            return;
        }
*/
        // showProgressDialog();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            //Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                            //   Toast.LENGTH_SHORT).show();

                            // updateUI(null);
                        }

                        // [START_EXCLUDE]
                        //hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }

}
