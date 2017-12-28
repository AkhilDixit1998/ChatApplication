package com.akhil.akhildixit.chat_app;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;




/*
public class CustomiseList extends BaseAdapter {


    Context context;
    Integer image;
    ArrayList<String> data;
    LayoutInflater inflater;
    ImageView imageView;

    CustomiseList(Context context, ArrayList<String> data)
    {
        this.context=context;
//        this.image=image;
        this.data=data;
        inflater= (LayoutInflater)this.context.getSystemService(this.context.LAYOUT_INFLATER_SERVICE);
    }







    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {

        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view=inflater.inflate(R.layout.customisedlistviewadapterlayout,null);
        TextView textView=(TextView)view.findViewById(R.id.listTextview);
        TextView textViewIcon=(TextView)view.findViewById(R.id.textViewIcon);
        imageView=(ImageView)view.findViewById(R.id.imageView);

        textView.setText(data.get(position));
        imageView.setImageResource(R.drawable.skitty);
        textViewIcon.setText("25");



        circularImageView(R.drawable.skitty);

        return view;



    }

    public void circularImageView(Integer image)
    {
        Resources res = context.getResources();
        Bitmap src = BitmapFactory.decodeResource(res, image);
        RoundedBitmapDrawable dr = RoundedBitmapDrawableFactory.create(res, src);
        */
/*dr.setCornerRadius(Math.max(src.getWidth(), src.getHeight()) / 2.0f);*//*


        dr.setCornerRadius(Math.min(dr.getMinimumWidth(), dr.getMinimumHeight()) / 2.0f);

        imageView.setImageDrawable(dr);

    }
}

*/









/**
 * Created by Akhil Dixit on 6/27/2017.
 */

public class CustomiseList extends BaseAdapter {


    Context context;
    String image_path;
    ArrayList<HashMap<String, String>> image;
    ArrayList<String> data;
    LayoutInflater inflater;
    ImageView imageView;
    int i = 1;
    String key;

    TextView last;

    String ms[];

    StorageReference mStorageRef;
    String imagePath_Dp;

    ProgressDialog progressDialog;


    CustomiseList(Context context, ArrayList<HashMap<String, String>> image, ArrayList data) {
        this.context = context;
        this.image = image;
        this.data = data;

        inflater = (LayoutInflater) this.context.getSystemService(this.context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {

        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        mStorageRef = FirebaseStorage.getInstance().getReference();

        if(view==null) {
            view = inflater.inflate(R.layout.customisedlistviewadapterlayout, null);
        }
            TextView textView = (TextView) view.findViewById(R.id.listTextview);
        TextView textViewIcon = (TextView) view.findViewById(R.id.textViewIcon);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        last = (TextView) view.findViewById(R.id.lastMessagesent);
        key = data.get(position);


                //lastMessage(key);

        //calcLastMessage(key,data);



        image_path = image.get(position).get(key);

        downlaod_files();

        loadImages(image_path);

        textView.setText(data.get(position));
        // Toast.makeText(context,mStorageRef.child(key).getName(),Toast.LENGTH_LONG).show();
       //textViewIcon.setText("25");


        // circularImageView(R.drawable.skitty);



        /*
        If want to use glide
        StorageReference storageRef =  storageReference.child("profile.jpg");

ImageView imageView = profilePicture;
// Load the image using Glide
Glide.with(context  context )
     .using(new FirebaseImageLoader())
                .load(storageRef)
                .into(imageView);*/


        return view;


    }

   /* public void circularImageView(Integer image)
    {
        Resources res = context.getResources();
        Bitmap src = BitmapFactory.decodeResource(res, image);
        RoundedBitmapDrawable dr = RoundedBitmapDrawableFactory.create(res, src);

dr.setCornerRadius(Math.max(src.getWidth(), src.getHeight()) / 2.0f);


        dr.setCornerRadius(Math.min(dr.getMinimumWidth(), dr.getMinimumHeight()) / 2.0f);

        imageView.setImageDrawable(dr);

    }*/

    public void loadImages(String path) {
        File storagePath = new File(Environment.getExternalStorageDirectory(), "DCIM/Chat");
        final File myFile = new File(storagePath, path);
        final String imagePath_Dp = myFile.getAbsolutePath();


        if (myFile.exists()) {
            try {

                Picasso.with(context).load(myFile)
                        .into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                Resources res = context.getResources();
                                Bitmap imageBitmap = null;

                                try {
                                    imageBitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                                } catch (Exception e) {
                                    //  Toast.makeText(context,"Exception is "+e,Toast.LENGTH_LONG).show();
                                }

                                RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(res, imageBitmap);

                                imageDrawable.setCornerRadius(Math.min(imageDrawable.getMinimumWidth(), imageDrawable.getMinimumHeight()) / 2.0f);

                                imageView.setImageDrawable(imageDrawable);
                                // Toast.makeText(context, "in picasso" + imagePath_Dp, Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onError() {
                                imageView.setImageResource(R.drawable.user1);
                            }
                        });

            } catch (Exception e) {
                imageView.setImageResource(R.drawable.user1);
            }
        }


    }


    public void downlaod_files()

    {
        File storagePath = new File(Environment.getExternalStorageDirectory(), "DCIM/Chat");
// Create direcorty if not exists
        if (!storagePath.exists()) {
            storagePath.mkdirs();
        }
        final File myFile = new File(storagePath, image_path);


        mStorageRef.child(image_path).getFile(myFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                // Toast.makeText(context,"here"+mStorageRef.child(image_path).getName(),Toast.LENGTH_LONG).show();

                //If we want to do something on sucees
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle errors
                //Toast.makeText(context,"Sorry file could not be downloaded  "+image_path,Toast.LENGTH_LONG).show();

                imageView.setImageResource(R.drawable.user1);
            }
        });
    }


    public void calcLastMessage(final String key, ArrayList<String> data) {


        // Toast.makeText(context,"in cal",Toast.LENGTH_LONG).show();

        int i = 0;

        while (!(key.equals(data.get(i)) && i <= data.size()))

        {

            Query lq = FirebaseDatabase.getInstance().getReference("messages").child(UserDetails.username).child(UserDetails.username + "_" + key).orderByKey().limitToLast(1);

            lq.addChildEventListener(new com.google.firebase.database.ChildEventListener() {
                @Override
                public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                    if (dataSnapshot.hasChild("message")) {

                        String message = dataSnapshot.child("message").getValue().toString();
                        ms = message.split("    ");

                        Toast.makeText(context, "in calc " + " key " + key + ms[0], Toast.LENGTH_LONG).show();


                        last.setText(ms[0]);
                        //  Toast.makeText(context,ms[0],Toast.LENGTH_LONG).show();

                    } else
                        Toast.makeText(context, "No messages found", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(com.google.firebase.database.DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(context, "Some error occured", Toast.LENGTH_LONG).show();

                }
            });
            i++;


        }

    }

    public void lastMessage(String key) {
        FirebaseDatabase.getInstance().getReference("messages").child(UserDetails.username)
                .child(UserDetails.username + "_" + key).child("last_msg").addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {

                if ((dataSnapshot.getValue() != null)) {
                    String msg = dataSnapshot.getValue().toString();
                    last.setText(msg);
                    // Toast.makeText(context, "In the user " +  dataSnapshot.getValue(), Toast.LENGTH_LONG).show();
                } else last.setText("No messages sent");
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                // last.setText("No messages sent");

            }
        });

    }

    }



