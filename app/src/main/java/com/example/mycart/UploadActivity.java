package com.example.mycart;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mycart.Model.ItemDetail;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadActivity extends AppCompatActivity
{
    EditText ename,eprice;
    Button selectimage,upload;
    ImageView imageView;
    FirebaseFirestore mStore;
    FirebaseDatabase database;

    DatabaseReference reference;

    StorageReference storageReference;

    Uri file_path;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        ename=findViewById(R.id.ename);
        eprice=findViewById(R.id.eprice);
        imageView=findViewById(R.id.imageView2);
        selectimage=findViewById(R.id.button001);
        upload=findViewById(R.id.button2);
        mStore=FirebaseFirestore.getInstance();

        database=FirebaseDatabase.getInstance();
        reference=database.getReference("data");
        storageReference= FirebaseStorage.getInstance().getReference();

        selectimage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
               Intent i= new Intent();
               i.setType("images/*");
               i.setAction(Intent.ACTION_GET_CONTENT);
               startActivityForResult(i,0);
            }
        });
        upload.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                ProgressDialog pd = new ProgressDialog(UploadActivity.this);
                pd.setMessage("Please wait");
                pd.setCanceledOnTouchOutside(false);
                pd.show();


                StorageReference ref=storageReference.child("Images/"+
                 System.currentTimeMillis()+"."+getFileExtension(file_path));

                ref.putFile(file_path).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
                 {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                    {
                        Task<Uri> firebaseUri=taskSnapshot.getStorage().getDownloadUrl();
                        firebaseUri.addOnSuccessListener(new OnSuccessListener<Uri>()
                        {
                            @Override
                            public void onSuccess(Uri uri)
                            {
                                   String name=ename.getText().toString();
                                   String img_url=uri.toString();
                                   int price= Integer.parseInt(eprice.getText().toString());
                                ItemDetail detail=new ItemDetail(name,img_url,price);

                                    String childId=reference.push().getKey();
                                assert childId != null;
                                reference.child(childId).setValue(detail);
                                    pd.dismiss();

                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(UploadActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

     @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
   {
        super.onActivityResult(requestCode, resultCode, data);

     if (requestCode==0 && resultCode==RESULT_OK && data!=null)
        {
           file_path=data.getData();
           imageView.setImageURI(file_path);
        }
     else
     {
         Toast.makeText(this, "Please select image", Toast.LENGTH_SHORT).show();
     }


    }
    public String getFileExtension(Uri file_path)
    {
        ContentResolver cr=getContentResolver();
        MimeTypeMap map=MimeTypeMap.getSingleton();
        return map.getExtensionFromMimeType(cr.getType(file_path));
    }
}