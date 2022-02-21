package com.arbaz.pusiapp.ui.chat;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.arbaz.pusiapp.Medical_chats;
import com.arbaz.pusiapp.R;
import com.arbaz.pusiapp.Sports_chat;
import com.arbaz.pusiapp.nonTech_chatGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class ChatFragment extends Fragment {

LinearLayout technical,nontechnical,medical,sports;
ScrollView scrollView;

    private FirebaseAuth auth;
    FirebaseDatabase database;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_chat, container, false);

        technical = view.findViewById(R.id.technical);
        nontechnical = view.findViewById(R.id.nontechnical);
        medical = view.findViewById(R.id.medical);
        sports = view.findViewById(R.id.sports);
        scrollView = view.findViewById(R.id.scrollview);
        database = FirebaseDatabase.getInstance();

        auth = FirebaseAuth.getInstance();

        FirebaseUser user = auth.getCurrentUser();

        if (user.isEmailVerified()){
            scrollView.setVisibility(View.VISIBLE);

        }else
        {
            Toast.makeText(getContext(), "Verify your email to Chat", Toast.LENGTH_SHORT).show();
        }



        technical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GroupChatActivity.class);
                startActivity(intent);
            }
        });

        nontechnical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), nonTech_chatGroup.class);
                startActivity(intent);
            }
        });

        medical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Medical_chats.class);
                startActivity(intent);
            }
        });

        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Sports_chat.class);
                startActivity(intent);
            }
        });




        return view;
    }
}