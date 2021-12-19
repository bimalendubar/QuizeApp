package com.example.quizeapp.profileActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizeapp.LoginActivity;
import com.example.quizeapp.MainActivity;
import com.example.quizeapp.R;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.quizeapp.R.id.profileLogOut_id;

public class ProfileActivity extends AppCompatActivity {
    ImageView profileImg_id;
    TextView profileEmail_id;
    TextView profileLogOut_id;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth=FirebaseAuth.getInstance();

        profileImg_id = this.<ImageView>findViewById(R.id.profileImg_id);
        profileEmail_id = this.<TextView>findViewById(R.id.profileEmail_id);
        profileLogOut_id = this.<TextView>findViewById(R.id.profileLogOut_id);


        profileEmail_id.setText(firebaseAuth.getCurrentUser().getEmail());

        profileLogOut_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    final AlertDialog.Builder builder=new AlertDialog.Builder(ProfileActivity.this);
                    builder.setMessage(" Log Out Now?");
                    builder.setCancelable(true);

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            firebaseAuth.signOut();
                            Toast.makeText(ProfileActivity.this, "LOG OUT", Toast.LENGTH_SHORT).show();

                            Intent intent = new  Intent(ProfileActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finishAffinity();
                            //finish();
                        }
                    });

                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();

            }
        });
    }
}