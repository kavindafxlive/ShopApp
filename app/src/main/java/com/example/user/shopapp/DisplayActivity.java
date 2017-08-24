package com.example.user.shopapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by USER on 8/24/2017.
 */
public class DisplayActivity extends AppCompatActivity {

    Button ASI,ASUI, Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        ASI = (Button)findViewById(R.id.btn_ASI);
        ASUI=(Button) findViewById(R.id.btn_ASUI);
        Logout=(Button) findViewById(R.id.btn_logout);

        ASI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentRegistration=new Intent(getApplicationContext(),AddItemActivity.class);
                startActivity(intentRegistration);

            }

        });

        ASUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentRegistration=new Intent(getApplicationContext(),AddBidActivity.class);
                startActivity(intentRegistration);


            }

        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            }

        });
    }
}
