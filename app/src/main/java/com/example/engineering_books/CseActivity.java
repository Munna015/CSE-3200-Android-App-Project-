package com.example.engineering_books;

import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CseActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cseactivity);
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("pdffile").child("CSE");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



       /*databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @androidx.annotation.Nullable String s) {
                String fileName=dataSnapshot.getKey();
                String url=dataSnapshot.getValue(String.class);
                ((MyAdapter)recyclerView.getAdapter()).update(fileName,url);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @androidx.annotation.Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @androidx.annotation.Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/


        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(CseActivity.this));
        MyAdapter myAdapter=new MyAdapter(recyclerView,CseActivity.this,new ArrayList<String>(),new ArrayList<String>());
        recyclerView.setAdapter(myAdapter);
    }
}

