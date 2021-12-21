package com.example.engineering_books;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Upload extends AppCompatActivity {
    private Button select,upload,upload1,upload2,upload3;
    private TextView text;
    Uri pdfuri;
    private FirebaseStorage storage;
    private FirebaseDatabase firebaseDatabase;
    private EditText editText;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        storage=FirebaseStorage.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        select=(Button)findViewById(R.id.Selectbutton);
        upload=(Button)findViewById(R.id.Upoloadbutton1);
        upload1=(Button)findViewById(R.id.Upoloadbutton2);
        upload2=(Button)findViewById(R.id.Upoloadbutton3);
        upload3=(Button)findViewById(R.id.Upoloadbutton4);
        text=(TextView)findViewById(R.id.fileName);
        editText=(EditText)findViewById(R.id.edit);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(Upload.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
                {
                    selectPdf();
                }
                else
                    ActivityCompat.requestPermissions(Upload.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pdfuri!=null)
                    uploadpdf(pdfuri);
                else
                    Toast.makeText(Upload.this,"select the file",Toast.LENGTH_SHORT).show();
            }
        });
        upload1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pdfuri!=null)
                    uploadpdf1(pdfuri);
                else
                    Toast.makeText(Upload.this,"select the file",Toast.LENGTH_SHORT).show();
            }
        });
        upload2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pdfuri!=null)
                    uploadpdf2(pdfuri);
                else
                    Toast.makeText(Upload.this,"select the file",Toast.LENGTH_SHORT).show();
            }
        });
        upload3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pdfuri!=null)
                    uploadpdf3(pdfuri);
                else
                    Toast.makeText(Upload.this,"select the file",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void uploadpdf(Uri pdfuri) {
        progressDialog=new  ProgressDialog(this);
        progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading File...");
        progressDialog.setProgress(0);
        progressDialog.show();
        final StorageReference storageReference=storage.getReference();
        final String fileName=System.currentTimeMillis()+".pdf";
        storageReference.child("CSE").child(fileName).putFile(pdfuri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                       final String Name = editText.getText().toString().trim();
                        String url=storageReference.getDownloadUrl().toString();

                        Log.d("TAG", url);
                        Log.d("TAG", taskSnapshot.getMetadata().toString());
                        Log.d("TAG", taskSnapshot.getMetadata().getReference().toString());
                        Log.d("TAG", taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());


                        DatabaseReference databaseReference=firebaseDatabase.getReference();
                        databaseReference.child("CSE").child(Name).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                    Toast.makeText(Upload.this,"Upload succesfully",Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(Upload.this,"Upload is not succesfully",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Upload.this,"Upload is succesfully",Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                 int currentprogress= (int) ((int)100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                 progressDialog.setProgress(currentprogress);
            }
        });


    }
    private void uploadpdf1(Uri pdfuri) {
        progressDialog=new  ProgressDialog(this);
        progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading File...");
        progressDialog.setProgress(0);
        progressDialog.show();
        StorageReference storageReference=storage.getReference();
        final String fileName=System.currentTimeMillis()+".pdf";
        storageReference.child("EEE").child(fileName).putFile(pdfuri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        final String Name=editText.getText().toString().trim();
                        String url=taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                        DatabaseReference databaseReference=firebaseDatabase.getReference();
                        databaseReference.child("EEE").child(Name).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                    Toast.makeText(Upload.this,"Upload succesfully",Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(Upload.this,"Upload is not succesfully",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Upload.this,"Upload is succesfully",Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                int currentprogress= (int) ((int)100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentprogress);
            }
        });
    }
    private void uploadpdf2(Uri pdfuri) {
        progressDialog=new  ProgressDialog(this);
        progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading File...");
        progressDialog.setProgress(0);
        progressDialog.show();
        StorageReference storageReference=storage.getReference();
        final String fileName=System.currentTimeMillis()+".pdf";
        storageReference.child("ME").child(fileName).putFile(pdfuri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        final String Name=editText.getText().toString().trim();
                        String url=taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                        DatabaseReference databaseReference=firebaseDatabase.getReference();
                        databaseReference.child("ME").child(Name).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                    Toast.makeText(Upload.this,"Upload succesfully",Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(Upload.this,"Upload is not succesfully",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Upload.this,"Upload is succesfully",Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                int currentprogress= (int) ((int)100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentprogress);
            }
        });
    }
    private void uploadpdf3(Uri pdfuri) {
        progressDialog=new  ProgressDialog(this);
        progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading File...");
        progressDialog.setProgress(0);
        progressDialog.show();
        StorageReference storageReference=storage.getReference();
        final String fileName=System.currentTimeMillis()+".pdf";
        storageReference.child("CIVIL").child(fileName).putFile(pdfuri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        final String Name=editText.getText().toString().trim();
                        String url=taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                        DatabaseReference databaseReference=firebaseDatabase.getReference();
                        databaseReference.child("CIVIL").child(Name).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                    Toast.makeText(Upload.this,"Upload succesfully",Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(Upload.this,"Upload is not succesfully",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Upload.this,"Upload is succesfully",Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                int currentprogress= (int) ((int)100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentprogress);
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==9 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            selectPdf();
        }
        else
            Toast.makeText(Upload.this,"please give permission",Toast.LENGTH_SHORT).show();
    }

    private void selectPdf() {
        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,86);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==86 && resultCode==RESULT_OK && data!=null)
        {
          pdfuri=data.getData();
          text.setText("A file is selected"+data.getData().getLastPathSegment());
        }
        Toast.makeText(Upload.this,"please Select a file",Toast.LENGTH_SHORT).show();
    }
}
