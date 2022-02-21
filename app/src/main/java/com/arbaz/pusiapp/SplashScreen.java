package com.arbaz.pusiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 2000;

    Animation topanim, bottomanim , leftanim , rightanim;
    ImageView splashimage;
    TextView splashpu , splashpuuu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // animaations
        topanim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomanim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        leftanim = AnimationUtils.loadAnimation(this, R.anim.left_animation);
        rightanim = AnimationUtils.loadAnimation(this, R.anim.right_anim);

        //declaring
        splashimage = findViewById(R.id.splashimage);
        splashpu = findViewById(R.id.splashpu);
        splashpuuu = findViewById(R.id.splashpuuu);


        splashimage.setAnimation(rightanim);
        splashpu.setAnimation(leftanim);
        splashpuuu.setAnimation(bottomanim);




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    // User is signed in
                    // Start home activity
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                } else {
                    // No user is signed in
                    // start login activity
                    startActivity(new Intent(SplashScreen.this, SignIn.class));
                }

                // close splash activity
                finish();
            }


        },SPLASH_SCREEN);
    }
}