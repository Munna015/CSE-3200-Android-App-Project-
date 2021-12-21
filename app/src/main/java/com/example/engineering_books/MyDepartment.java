package com.example.engineering_books;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyDepartment extends AppCompatActivity {
    ListView listView;
    DatabaseReference databaseReference;
    List<UploadHandler> uploadPdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_department);
        listView=(ListView)findViewById(R.id.list);
        uploadPdf=new ArrayList<>();
        viewAllfiles();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UploadHandler uploadHandler=uploadPdf.get(position);
                Intent intent=new Intent();
                intent.setData(Uri.parse( uploadHandler.getPdfName()));
                startActivity(intent);
            }
        });
    }

    private void viewAllfiles() {
        databaseReference= FirebaseDatabase.getInstance().getReference().child("pdffile").child("CSE");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postsnapshot:dataSnapshot.getChildren()) {
                    UploadHandler uploadHandler = postsnapshot.getValue(UploadHandler.class);
                    uploadPdf.add(uploadHandler);
                }
                String [] uploads=new String[uploadPdf.size()];
                for(int i=0;i<uploads.length;i++)
                {
                    uploads[i]=uploadPdf.get(i).getPdfName();
                }
                ArrayAdapter <String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,uploads)
                {

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view=super.getView(position, convertView, parent);
                        TextView textView=(TextView)view.findViewById(android.R.id.text1);
                        textView.setTextColor(Color.parseColor("#FFFFFF"));;
                        return super.getView(position, convertView, parent);
                    }
                };
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
