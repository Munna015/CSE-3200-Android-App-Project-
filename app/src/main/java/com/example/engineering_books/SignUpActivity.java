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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    int progess_initial=0;
    private EditText editText1,editText2,editText3,editText4,editText5;
    private Button button;
    private TextView textView;
    private ProgressBar progressBar1;
  FirebaseAuth firebaseAuth;
  DatabaseReference databaseReference;

    Member member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        button=(Button)findViewById(R.id.angry_btn1);
        textView=(TextView)findViewById(R.id.signview) ;
        editText1=(EditText)findViewById(R.id.e1);
        editText2=(EditText)findViewById(R.id.e2);
        editText3=(EditText)findViewById(R.id.e3);
        editText4=(EditText)findViewById(R.id.e4);
        editText5=(EditText)findViewById(R.id.e5);
        progressBar1=(ProgressBar)findViewById(R.id.progress1);
        databaseReference=FirebaseDatabase.getInstance().getReference("Member");
        getSupportActionBar().hide();

        firebaseAuth=FirebaseAuth.getInstance();
        member=new Member();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserRegister();

            }

        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });

    }

    private void UserRegister() {
        String username=editText1.getText().toString().trim();
        String rollnumber=editText2.getText().toString().trim();
        String email=editText3.getText().toString().trim();
        String password=editText4.getText().toString().trim();
        String re_typepassword=editText5.getText().toString().trim();
        if(username.isEmpty())
        {
            editText1.setError("Enter Username");
            editText1.requestFocus();
            return;
        }
        else if(rollnumber.isEmpty())
        {
            editText2.setError("Enter Rollnumber");
            editText2.requestFocus();
            return;
        }
        else if(email.isEmpty())
        {
            editText3.setError("Enter Email");
            editText3.requestFocus();
            return;
        }
        else if(password.isEmpty())
        {
            editText4.setError("Enter Password");
            editText4.requestFocus();
            return;
        }
        else if(re_typepassword.isEmpty())
        {
            editText5.setError("Enter Re_type password");
            editText5.requestFocus();
            return;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            editText3.setError("Enter Valid email");
            editText3.requestFocus();
            return;
        }
        else if(password.length()<6)
        {
            editText4.setError("Minimum length of password should be 6");
            editText4.requestFocus();
            return;
        }
        else if(!password.equals(re_typepassword))
        {
            editText5.setError("Password and Re_Type password doesn't match");
            editText5.requestFocus();
            return;
        }
       else
        {
            datasave();
            progressBar1.setVisibility(View.VISIBLE);
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressBar1.setVisibility(View.INVISIBLE);
                  if(!task.isSuccessful())
                  {
                      if(task.getException() instanceof FirebaseAuthUserCollisionException)
                      {
                          Toast.makeText(SignUpActivity.this,"Email is already used",Toast.LENGTH_SHORT).show();
                      }
                      else
                      {
                          Toast.makeText(SignUpActivity.this,"Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                      }
                  }
                  else
                  {

                      Toast.makeText(SignUpActivity.this,"SignUp seccessfully",Toast.LENGTH_SHORT).show();
                      Intent intent2=new Intent(SignUpActivity.this,MainActivity.class);
                      intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                      startActivity(intent2);
                  }
                }

            });
        }



    }

    private void datasave() {
        String username=editText1.getText().toString().trim();
        String rollnumber=editText2.getText().toString().trim();
        String email=editText3.getText().toString().trim();
        String password=editText4.getText().toString().trim();
        String Key=databaseReference.push().getKey();
        Member member=new Member();
        member.setUser(username);
        member.setRoll(rollnumber);
        member.setEmai(email);
        member.setPass(password);
        databaseReference.child(Key).setValue(member);

    }


}
