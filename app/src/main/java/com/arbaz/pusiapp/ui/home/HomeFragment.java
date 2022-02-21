package com.arbaz.pusiapp.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.arbaz.pusiapp.MainActivity;
import com.arbaz.pusiapp.R;
import com.arbaz.pusiapp.SignIn;
import com.arbaz.pusiapp.SignUp;
import com.google.firebase.auth.FirebaseAuth;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;


public class HomeFragment extends Fragment {

private SliderLayout sliderLayout;
private CardView logoutbtn;

private long backPressedTime;
FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_home, container, false);

       sliderLayout = view.findViewById(R.id.slider);
       logoutbtn = view.findViewById(R.id.logoutbtn);
        auth =FirebaseAuth.getInstance();
       sliderLayout.setIndicatorAnimation(IndicatorAnimations.THIN_WORM);
       sliderLayout.setSliderTransformAnimation(SliderAnimations.ZOOMOUTTRANSFORMATION);
       sliderLayout.setScrollTimeInSec(2);


       setSliderViews();

       logoutbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                auth.signOut();
               Intent intent = new Intent(getContext(), SignIn.class);
               startActivity(intent);
           }
       });

       return view;
    }

    private void setSliderViews() {
            for (int i = 0; i< 5 ; i++){
                DefaultSliderView sliderView = new DefaultSliderView(getContext());

                switch (i){
                    case 0:
                        //sliderView.setDescription();
                        sliderView.setImageUrl("https://paruluniversity.ac.in/files/4/predone%20ng.png");
                                break;
                    case 1:
                        //sliderView.setDescription();
                        sliderView.setImageUrl("https://paruluniversity.ac.in/files/4/2.png");
                        break;
                    case 2:
                        //sliderView.setDescription();
                        sliderView.setImageUrl("https://static.toiimg.com/thumb/80284031.cms?width=680&height=512&imgsize=1072283");
                        break;
                    case 3:
                        //sliderView.setDescription();
                        sliderView.setImageUrl("https://paruluniversity.ac.in/files/4/7.png");
                        break;
                    case 4:
                        //sliderView.setDescription();
                        sliderView.setImageUrl("https://paruluniversity.ac.in/files/4/7_.png");
                        break;
                }
                sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);

                sliderLayout.addSliderView(sliderView);

            }
    }




}