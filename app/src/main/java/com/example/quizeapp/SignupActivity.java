package com.example.quizeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignupActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    EditText et_email,et_password,et_confirmpassword;
    Button btn_singup;
    TextView login_page;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseAuth=FirebaseAuth.getInstance();

        login_page=findViewById(R.id.singup_loginpage_id);
        btn_singup=findViewById(R.id.singup_btn_id);




        login_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singupUser();
            }
        });




    }

    private void singupUser() {
        et_email= findViewById(R.id.singup_email_id);
        et_password= findViewById(R.id.singup_pswd_id);
        et_confirmpassword=findViewById(R.id.singup_Confirm_pswd_id);

        String email,password,confirmpassword;
        email=et_email.getText().toString();
        password=et_password.getText().toString();
        confirmpassword=et_confirmpassword.getText().toString();

        if(email.isEmpty()||password.isEmpty()||confirmpassword.isEmpty())
        {
            Toast.makeText(this, "email and password can't be blank", Toast.LENGTH_SHORT).show();
            return;
        }
        if(confirmpassword != password){
            Toast.makeText(this, "password don't match", Toast.LENGTH_SHORT).show();



        }
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Toast.makeText(SignupActivity.this, "Singup Successful", Toast.LENGTH_SHORT).show();
                    Intent  intent=new Intent(SignupActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }
                else {
                    Toast.makeText(SignupActivity.this, "error creating user", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder=new AlertDialog.Builder(SignupActivity.this);
        builder.setMessage("Are You Quit?");
        builder.setCancelable(true);
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("y", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
                //finish();
            }
        });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}