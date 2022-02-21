package com.arbaz.pusiapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.arbaz.pusiapp.Adapter.ChatAdapter;
import com.arbaz.pusiapp.Models.MessageModel;
import com.arbaz.pusiapp.ui.chat.GroupChatActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class Medical_chats extends AppCompatActivity {

    ImageView backarrow;
    RecyclerView recyclerv;
    ImageView sendbtn;
    EditText edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_chats);
        recyclerv = findViewById(R.id.recyclerv);
        sendbtn = findViewById(R.id.sendbtnn);
        edittext = findViewById(R.id.edittext);


        backarrow = findViewById(R.id.backarrow);




        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Medical_chats.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final ArrayList<MessageModel> messageModels = new ArrayList<>();

        final String senderId = FirebaseAuth.getInstance().getUid();



        final ChatAdapter adapter = new ChatAdapter(messageModels, this);
        recyclerv.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        // focusing last postion of message
        layoutManager.setStackFromEnd(true);
        //end
        recyclerv.setLayoutManager(layoutManager);



        database.getReference().child("Medical Chat")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        messageModels.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            MessageModel model = dataSnapshot.getValue(MessageModel.class);
                            messageModels.add(model);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String message = edittext.getText().toString();
                final String userNamee = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
                final MessageModel model = new MessageModel(senderId , message , userNamee);
                model.setTimestamp(new Date().getTime());

                edittext.setText("");

                database.getReference().child("Group Chat")
                        .push()
                        .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                });
            }
        });

    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        Intent intent = new Intent(Medical_chats.this, MainActivity.class);
        startActivity(intent);
    }
}