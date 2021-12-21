package com.example.engineering_books.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.engineering_books.CivilActivity;
import com.example.engineering_books.CseActivity;
import com.example.engineering_books.EeeActivity;
import com.example.engineering_books.MeActivity;
import com.example.engineering_books.MyDepartment;
import com.example.engineering_books.R;

public class HomeFragment extends Fragment {

    public HomeViewModel homeViewModel;
    public ImageButton imageButton1;
    public ImageButton imageButton2;
    public ImageButton imageButton3;
    public ImageButton imageButton4;
   public TextView textView1;
    public TextView textView2;
    public TextView textView3;
   public TextView textView4;


    public View onCreateView(LayoutInflater inflater,
                                            ViewGroup container, Bundle savedInstanceState) {


        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        imageButton1=(ImageButton)root.findViewById(R.id.image1);
        imageButton2=(ImageButton)root.findViewById(R.id.image2);
        imageButton3=(ImageButton)root.findViewById(R.id.image3);
        imageButton4=(ImageButton)root.findViewById(R.id.image4);
        textView1=(TextView)root.findViewById(R.id.View1);
        textView2=(TextView)root.findViewById(R.id.View2);
        textView3=(TextView)root.findViewById(R.id.View3);
        textView4=(TextView)root.findViewById(R.id.View4);

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),CseActivity.class);
                startActivity(intent);
            }
        });
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(getActivity(),CseActivity.class);
                startActivity(intent);
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), EeeActivity.class);
                startActivity(intent);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), EeeActivity.class);
                startActivity(intent);
            }
        });
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MeActivity.class);
                startActivity(intent);
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MeActivity.class);
                startActivity(intent);
            }
        });
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), CivilActivity.class);
                startActivity(intent);
            }
        });
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),CivilActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }



    }

