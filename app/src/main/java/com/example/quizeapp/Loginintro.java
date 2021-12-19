package com.example.quizeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

public class Loginintro extends AppCompatActivity {
    FirebaseAuth firebaseAuth;

    Button btn_getstarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginintro);

        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            Toast.makeText(this, "User already logged in!", Toast.LENGTH_SHORT).show();
                redirectPage("MAIN");
        }

        btn_getstarted= this.<Button>findViewById(R.id.btn_Getstarted_Id);

       /* Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Loginintro.this,SignupActivity.class);
                startActivity(intent);
                finish();

            }
        },2000);*/



        btn_getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    redirectPage("SINGUP");

            }
        });
    }

    private void redirectPage(String name)  {
        Intent intent;
        switch (name) {
            case "SINGUP":
                intent = new Intent(Loginintro.this,SignupActivity.class);
                startActivity(intent);
                finish();
                break;
            case "MAIN":
                intent = new Intent(Loginintro.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

            default:    Toast.makeText(this, "no path exists", Toast.LENGTH_SHORT).show();

        }




    }
}