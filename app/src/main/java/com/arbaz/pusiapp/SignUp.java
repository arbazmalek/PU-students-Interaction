package com.arbaz.pusiapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    CardView btnsignup;
    TextView etemail ,pumail, etpassword , Username ;
    RelativeLayout  alreadyaccount ;
    private long backPressedTime;
    private Toast backtoast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnsignup = findViewById(R.id.btnSignup);
        etemail = findViewById(R.id.etEmail);
        pumail = findViewById(R.id.pumail);
        etpassword = findViewById(R.id.etPassword);
        Username = findViewById(R.id.username);
        alreadyaccount = findViewById(R.id.alreadyaccount);

// auth instance
        auth = FirebaseAuth.getInstance();
        database =FirebaseDatabase.getInstance();


        //progressdialog code
        progressDialog = new ProgressDialog(SignUp.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("Creating your account");

        String perfect = etemail.getText().toString().concat(pumail.getText().toString());


        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                auth.createUserWithEmailAndPassword
                        (etemail.getText().toString().concat(pumail.getText().toString()),etpassword.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()){

                                    //send verification link
                                    FirebaseUser user = auth.getCurrentUser();
                                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(SignUp.this, "Verification Email has been sent", Toast.LENGTH_SHORT).show();
                                            


                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(SignUp.this, "Email not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    //storing data in realtime database taking constructor from model users
                                    Users userr = new Users(Username.getText().toString(),etemail.getText().toString(),etpassword.getText().toString());
                                    //storing id in database from unique id firebase
                                    String id = task.getResult().getUser().getUid();
                                    database.getReference().child("Users").child(id).setValue(userr);


                                    //  Toast.makeText(SignUp.this, "User created successfully", Toast.LENGTH_SHORT).show();



                                }

                            }
                        });
            }
        });

        alreadyaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,SignIn.class);
                startActivity(intent);
            }
        });




    }

    @Override
    public void onBackPressed() {



        if (backPressedTime +2000 > System.currentTimeMillis()){
            backtoast.cancel();
            super.onBackPressed();
            finishAffinity();
            return;
        }else {
           backtoast = Toast.makeText(SignUp.this, "press back again to exit", Toast.LENGTH_SHORT);
                   backtoast.show();
        }
        backPressedTime = System.currentTimeMillis();

    }
    }
