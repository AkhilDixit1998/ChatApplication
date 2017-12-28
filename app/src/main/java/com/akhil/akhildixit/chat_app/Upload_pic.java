package com.akhil.akhildixit.chat_app;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Akhil Dixit on 6/29/2017.
 */

public class Upload_pic extends AppCompatActivity {


    private StorageReference mStorageRef;
    public static final int GET_FROM_GALLERY = 3;
    Uri selectedImage;
    Intent inte;
    String username;
    ProgressDialog progressDialog;
    NotificationManager mNotifyManager;
    NotificationCompat.Builder mBuilder;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.design_navigation_item_separator);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        MarshMallowPermission marshMallowPermission=new MarshMallowPermission(this);
        inte=getIntent();
       username= inte.getStringExtra("Username");
        progressDialog=new ProgressDialog(this);

        mNotifyManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("Picture Download")
                .setContentText("Download in progress")
                .setSmallIcon(R.mipmap.ic_launcher);


        open_Gallery();

    }

    public void open_Gallery()
    {
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
    }




    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            selectedImage = data.getData();

            progressDialog.show();
            upload_files();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
            } catch (FileNotFoundException e) {

                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }

        }
    }



    public void upload_files()
    {
        MarshMallowPermission marshMallowPermission=new MarshMallowPermission(this);
        marshMallowPermission.requestPermissionForExternalStorage();
        //Uri file = Uri.fromFile(new File(filepath));

        Uri file=selectedImage;
       StorageReference riversRef = mStorageRef.child(username+".jpg");

        riversRef.putFile(file).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();

                        Toast.makeText(Upload_pic.this,"upload success",Toast.LENGTH_LONG).show();
                        finish();
                        Log.d("Debug","Succes files");
                        progressDialog.dismiss();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads

                        Log.d("Error","file didnt upload");
                        // ...

                    }
                });
    }


}
