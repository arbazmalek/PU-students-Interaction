package com.arbaz.pusiapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {

    ProgressDialog progressDialog;
    FirebaseAuth auth;
     CardView btnsignin;
     RelativeLayout tvclicksignup;
    TextView etEmail , etPassword , Username ;

    private long backPressedTime;
    private Toast backtoast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btnsignin = findViewById(R.id.btnSignin);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        Username = findViewById(R.id.username);
        tvclicksignup = findViewById(R.id.tvclicksignup);





        auth = FirebaseAuth.getInstance();

        FirebaseUser user = auth.getCurrentUser();

        //progressdialog code
        progressDialog = new ProgressDialog(SignIn.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Logging in");



        //google signin code end

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // email and password not enter error
                if (etEmail.getText().toString().isEmpty()){
                    etEmail.setError("Please enter email");
                    return;
                }
                if (etPassword.getText().toString().isEmpty()){
                    etPassword.setError("Please enter password");
                    return;
                }

                progressDialog.show();

                auth.signInWithEmailAndPassword(etEmail.getText().toString(),etPassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()){
                                    Intent intent = new Intent(SignIn.this,MainActivity.class);
                                    startActivity(intent);
                                }
                                else {
                                   Toast.makeText(SignIn.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        tvclicksignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this,SignUp.class);
                startActivity(intent);
            }
        });



        // code to not getting login again after once login
//        if (auth.getCurrentUser()!=null){
//            Intent intent = new Intent(SignIn.this,MainActivity.class);
//            startActivity(intent);
//        }

    }

    @Override
    public void onBackPressed() {



        if (backPressedTime +2000 > System.currentTimeMillis()){
            backtoast.cancel();
            super.onBackPressed();
            finishAffinity();
            return;
        }else {
           backtoast =  Toast.makeText(SignIn.this, "press back again to exit", Toast.LENGTH_SHORT);
                    backtoast.show();
        }
        backPressedTime = System.currentTimeMillis();

    }
}