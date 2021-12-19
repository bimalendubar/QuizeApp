package com.example.quizeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.jvm.internal.Intrinsics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.quizeapp.models.Quiz;
import com.example.quizeapp.profileActivity.ProfileActivity;
import com.example.quizeapp.questionActivity.QuestionActivity;
import com.example.quizeapp.quizAdapter.QuizAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;

    View materialDatePicker;

    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    AppBarLayout appBarLayout;
    MaterialToolbar materialToolbar;
    NavigationView navigationView;
    QuizAdapter quizAdapter;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    //HashMap<String,Question> hashMap=new HashMap<>();
    List<Quiz> list=new ArrayList<Quiz>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appBarLayout= findViewById(R.id.appBarLayout_id);
        recyclerView=findViewById(R.id.quizRecyclerView_id);
        navigationView=findViewById(R.id.navigationView_id);
        materialDatePicker = findViewById(R.id.materialDatePicker_id);

        gridLayoutManager=new GridLayoutManager(this,2);


       // setUpDammyData();
        setUpViews();



    }

   /* private void setUpDammyData(){
    list.add(new Quiz("11-12-21","11-12-21"));
    list.add(new Quiz("12-12-21","12-12-21"));
    list.add(new Quiz("13-12-21","13-12-21"));
    list.add(new Quiz("14-12-21","14-12-21"));
    list.add(new Quiz("15-12-21","15-12-21"));
    list.add(new Quiz("11-12-21","11-12-21"));
    list.add(new Quiz("12-12-21","12-12-21"));
    list.add(new Quiz("13-12-21","13-12-21"));
    list.add(new Quiz("14-12-21","14-12-21"));
    list.add(new Quiz("15-12-21","15-12-21"));
    list.add(new Quiz("11-12-21","11-12-21"));
    list.add(new Quiz("12-12-21","12-12-21"));
    list.add(new Quiz("13-12-21","13-12-21"));
    list.add(new Quiz("14-12-21","14-12-21"));
    list.add(new Quiz("15-12-21","15-12-21"));
    list.add(new Quiz("11-12-21","11-12-21"));
    list.add(new Quiz("12-12-21","12-12-21"));
    list.add(new Quiz("13-12-21","13-12-21"));
    list.add(new Quiz("14-12-21","14-12-21"));
    list.add(new Quiz("15-12-21","15-12-21"));
    list.add(new Quiz("11-12-21","11-12-21"));
    list.add(new Quiz("12-12-21","12-12-21"));
    list.add(new Quiz("13-12-21","13-12-21"));
    list.add(new Quiz("14-12-21","14-12-21"));
    list.add(new Quiz("15-12-21","15-12-21"));
    list.add(new Quiz("11-12-21","11-12-21"));
    list.add(new Quiz("12-12-21","12-12-21"));
    list.add(new Quiz("13-12-21","13-12-21"));
    list.add(new Quiz("14-12-21","14-12-21"));
    list.add(new Quiz("15-12-21","15-12-21"));


    }*/

    private void setUpViews() {
        setUpDatepicker();
        setUpFireStore();
        setUpDrawerLayout();
        setUpRecyclerView();



    }

    private void setUpDatepicker() {
        materialDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*MaterialDatePicker<Long> var10000 = MaterialDatePicker.Builder.datePicker().build();
                Intrinsics.checkExpressionValueIsNotNull(var10000, "MaterialDatePicker.Builder.datePicker().build()");
                MaterialDatePicker<Long> datePicker = var10000;
                datePicker.show(MainActivity.this.getSupportFragmentManager(), "DatePicker");
                datePicker.addOnPositiveButtonClickListener((MaterialPickerOnPositiveButtonClickListener<? super Long>) new MaterialPickerOnPositiveButtonClickListener<Long>() {

                    public final void onPositiveButtonClick(Long it) {
                        SimpleDateFormat dateFormater = new SimpleDateFormat("dd-MM-yyyy");
                        //Intrinsics.checkExpressionValueIsNotNull(it, "it");
                        String date = dateFormater.format(new Date(it));
                        Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                        intent.putExtra("DATE", date);
                        startActivity(intent);
                    }
                });*/


                MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker().build();
                datePicker.show(getSupportFragmentManager(),"DatePicker");

                datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        if(selection != null){
                            Log.d("DATEPICKER", datePicker.getHeaderText());
                            SimpleDateFormat dateFormater = new SimpleDateFormat("dd-MM-yyyy");
                            String date=dateFormater.format(new Date(selection));
                            Intent intent=new Intent(MainActivity.this, QuestionActivity.class);
                            intent.putExtra("DATE",date);
                            startActivity(intent);
                        }


                    }
                });


                datePicker.addOnNegativeButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("DATEPICKER", datePicker.getHeaderText());
                        Toast.makeText(MainActivity.this, "Empty", Toast.LENGTH_SHORT).show();

                    }
                });

                datePicker.addOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Log.d("DATEPICKER","DatePicker is cancelled");

                    }
                });



            }
        });

    }


    private void setUpFireStore() {
        firebaseFirestore=FirebaseFirestore.getInstance();
        CollectionReference collectionReference=firebaseFirestore.collection("quizzes");

        collectionReference.addSnapshotListener((value, error) -> {

            if(value==null||error!=null){
                Toast.makeText(this, "error fetching data", Toast.LENGTH_SHORT).show();
                return ;
            }
          //Log.d("DATA",value.toObjects(Quiz.class).toString());

            list.clear();

            list.addAll(value.toObjects(Quiz.class));
            quizAdapter.notifyDataSetChanged();

        });

    }

    private void setUpRecyclerView() {
        quizAdapter= new QuizAdapter(this, list);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(quizAdapter);

    }


    private void setUpDrawerLayout() {
        drawerLayout= this.<DrawerLayout>findViewById(R.id.drawerLayout_id);
        materialToolbar= this.<MaterialToolbar>findViewById(R.id.topAppBar_id);

        actionBarDrawerToggle= new ActionBarDrawerToggle(this,drawerLayout,materialToolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                Intent intent= new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Are You want to Quit?");
        builder.setCancelable(true);

        builder.setNegativeButton("no", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("yes", new OnClickListener() {
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