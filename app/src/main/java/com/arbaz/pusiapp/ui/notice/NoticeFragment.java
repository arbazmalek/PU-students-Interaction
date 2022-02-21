package com.arbaz.pusiapp.ui.notice;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arbaz.pusiapp.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NoticeFragment extends Fragment {

    RecyclerView feedrecycler , recycler_courses;
    ProgressBar progressBar;
    private ArrayList<NoticeData> list;
    private NoticeAdapter adapter;

    private DatabaseReference reference;

    ShimmerFrameLayout shimmerFrameLayout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notice, container, false);
        reference = FirebaseDatabase.getInstance().getReference().child("Notice");

        feedrecycler = view.findViewById(R.id.feedrecycler);


        shimmerFrameLayout = view.findViewById(R.id.shimmer_view_container);



        ArrayList<CourseModel> list = new ArrayList<>();
        list.add(new CourseModel( "Android",R.drawable.and ));
        list.add(new CourseModel( "AWS cloud",R.drawable.cloud ));
        list.add(new CourseModel( "website dev", R.drawable.web));
        list.add(new CourseModel( "Hacking",R.drawable.hack ));
        list.add(new CourseModel( "Azure cloud",R.drawable.azure ));


      //  CourseAdapter adapter = new CourseAdapter(list ,getContext());
        //recycler_courses.setAdapter(adapter);

//        LinearLayoutManager LayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
//        recycler_courses.setLayoutManager(LayoutManager);

















        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        feedrecycler.setLayoutManager(layoutManager);

        // latest at top code
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        // latest at top code endddd


        feedrecycler.setHasFixedSize(true);


        shimmerFrameLayout.stopShimmer();


        getNotice();

        return view;
    }

    private void getNotice() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    NoticeData data = snapshot.getValue(NoticeData.class);
                    list.add(data);
                }

//                adapter = new NoticeAdapter(getContext() , list);
//                adapter.notifyDataSetChanged();
//                progressBar.setVisibility(View.GONE);
//                feedrecycler.setAdapter(adapter);

                adapter = new NoticeAdapter(getContext(),list);
                adapter.notifyDataSetChanged();



                feedrecycler.setAdapter(adapter);
               shimmerFrameLayout.stopShimmer();
               shimmerFrameLayout.setVisibility(View.GONE);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }

//    @Override
//    public void onPause() {
//        shimmerFrameLayout.stopShimmer();
//        super.onPause();
//    }
//
//    @Override
//    public void onResume() {
//        shimmerFrameLayout.startShimmer();
//        super.onResume();
//
//    }
}