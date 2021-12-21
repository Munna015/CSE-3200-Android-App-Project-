package com.example.engineering_books;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    int initial;
    private Button loginbutton;
    private Button authorbutton;
    private TextView logintextview;
    private EditText loginedittext1,loginedittext2;
    private ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();
        loginbutton=(Button)findViewById(R.id.angry_btn);
        logintextview=(TextView)findViewById(R.id.loginview);
        loginedittext1=(EditText)findViewById(R.id.edit1);
        loginedittext2=(EditText)findViewById(R.id.edit2);
        progressBar=(ProgressBar)findViewById(R.id.progress);
        authorbutton=(Button)findViewById(R.id.author);
        getSupportActionBar().hide();
        authorbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Upload.class);
                startActivity(intent);
            }
        });

        logintextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email1=loginedittext1.getText().toString().trim();
                String password1=loginedittext2.getText().toString().trim();


               if(email1.isEmpty())
                {
                    loginedittext1.setError("Enter Email");
                    loginedittext1.requestFocus();
                    return;
                }
                else if(password1.isEmpty())
                {

                    loginedittext2.setError("Enter Password");
                   loginedittext2.requestFocus();
                    return;
                }

                else
                {
                    progressBar.setVisibility(View.VISIBLE);
                    firebaseAuth.signInWithEmailAndPassword(email1,password1).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if(!task.isSuccessful())
                                Toast.makeText(MainActivity.this,"Email or password is wrong,Try again",Toast.LENGTH_SHORT).show();
                            else
                            {

                                Toast.makeText(MainActivity.this,"Login seccessfully",Toast.LENGTH_SHORT).show();
                                Intent intent4=new Intent(MainActivity.this,NavigationActivity.class);
                                intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent4);

                            }
                        }

                    });
                }
            }
        });


    }


}
