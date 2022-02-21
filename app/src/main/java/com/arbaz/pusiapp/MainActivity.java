package com.arbaz.pusiapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.arbaz.pusiapp.ui.chat.ChatFragment;
import com.arbaz.pusiapp.ui.home.HomeFragment;
import com.arbaz.pusiapp.ui.notice.NoticeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private long backPressedTime;
    private Toast backtoast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container , new NoticeFragment()).commit();
     //   navController = Navigation.findNavController(this,R.id.frame_layout);

        bottomNavigationView.setSelectedItemId(R.id.nagviagtion_gallery);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                switch (item.getItemId()){
                    case R.id.navigation_noticee:
                        fragment = new NoticeFragment();
                        break;
                    case R.id.navigation_chatt:
                        fragment = new ChatFragment();
                        break;
                    case R.id.navigation_homee:
                        fragment = new HomeFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container , fragment).commit();

                return true;
            }
        });

     //   NavigationUI.setupWithNavController(bottomNavigationView , navController);
    }

    @Override
    public void onBackPressed() {

        if (backPressedTime +2000 > System.currentTimeMillis()){
            backtoast.cancel();
            super.onBackPressed();
            finishAffinity();
            return;
        }else {
          backtoast =  Toast.makeText(MainActivity.this, "press back again to exit", Toast.LENGTH_SHORT);
          backtoast.show();
        }
        backPressedTime = System.currentTimeMillis();

    }
}