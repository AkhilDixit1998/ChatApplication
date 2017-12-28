package com.akhil.akhildixit.chat_app;

/**
 * Created by Akhil Dixit on 6/27/2017.
 */

/*

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Chat extends AppCompatActivity {
    LinearLayout layout;
    ImageView sendButton;
    EditText messageArea;
    ScrollView scrollView;
    Firebase reference1, reference2;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    String is_online;
    String logged;
    ImageView chatDp;
    TextView chatName,chatStatus;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        layout = (LinearLayout) findViewById(R.id.layout1);
        sendButton = (ImageView) findViewById(R.id.sendButton);
        messageArea = (EditText) findViewById(R.id.messageArea);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        chatDp=(ImageView)findViewById(R.id.chatImageView);
        chatName=(TextView)findViewById(R.id.chatName);
        chatStatus=(TextView)findViewById(R.id.chatStatus);

        Toolbar toolbar=(Toolbar)findViewById(R.id.chattoolbar);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");
        // String is_online=databaseReference.child(UserDetails.username).child("Logged_In").toString();

        Firebase.setAndroidContext(this);
        reference1 = new Firebase("https://chatapp-1d3d6.firebaseio.com/messages/" + UserDetails.username + "_" + UserDetails.chatWith);
        reference2 = new Firebase("https://chatapp-1d3d6.firebaseio.com/messages/" + UserDetails.chatWith + "_" + UserDetails.username);


        final String name_of_chat = UserDetails.chatWith.toUpperCase();

        loadImages(UserDetails.chatWith+".jpg");





        databaseReference.child(UserDetails.chatWith).child("Logged_In").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {

                logged = dataSnapshot.getValue().toString();
                if (logged.equals("true"))
                {
                    is_online="ONLINE";
                    chatName.setText(name_of_chat);
                    chatStatus.setText(is_online);
               // Chat.this.setTitle(name_of_chat + " "+is_online);

                Toast.makeText(Chat.this, "In snapshot if "+UserDetails.chatWith+logged, Toast.LENGTH_LONG).show();
            }
            else {


                    databaseReference.child(UserDetails.chatWith).child("Last_seen").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {

                            String lastseen;
                            lastseen = dataSnapshot.getValue().toString();
                            if (logged.equals("false"))
                            {
                                chatName.setText(name_of_chat);
                                chatStatus.setText(lastseen);
                              //  Chat.this.setTitle(name_of_chat + " "+lastseen);
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                            Toast.makeText(Chat.this, "Error occured in reading data", Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(Chat.this, "Error occured in reading data", Toast.LENGTH_LONG).show();
            }

        });

        */
/*databaseReference.child(UserDetails.chatWith).child("Last_seen").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {

                String lastseen;
                lastseen = dataSnapshot.getValue().toString();
                if (logged.equals("false"))
                {
                    Chat.this.setTitle(name_of_chat + " "+lastseen);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(Chat.this, "Error occured in reading data", Toast.LENGTH_LONG).show();
            }
        });
*//*



        */
/*To add name of the person in title*//*


        sendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String messageText = messageArea.getText().toString();
                DateFormat dateFormat = new SimpleDateFormat(" HH:mm a");
                Calendar cal = Calendar.getInstance();
                String time = dateFormat.format(cal.getTime());


                if (!messageText.equals("")) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("message", messageText + "    " + time);
                    map.put("user", UserDetails.username);
                    reference1.push().setValue(map);
                    reference2.push().setValue(map);

                }

            }
        });

        reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map map = dataSnapshot.getValue(Map.class);
                String message = map.get("message").toString();
                String userName = map.get("user").toString();


                if (userName.equals(UserDetails.username)) {
                    // addMessageBox("You:-\n" + message, 1);    If we want to have the name also written
                    addMessageBox(message, 1);
                } else {
                    //addMessageBox(UserDetails.chatWith + ":-\n" + message, 2);
                    addMessageBox(message, 2);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void addMessageBox(String message, int type) {
        TextView textView = new TextView(Chat.this);
        textView.setText(message);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        lp.setMargins(10, 10, 10, 10);
//        textView.setLayoutParams(lp);


        //textView.setTextColor(Integer.parseInt("#2c3e50"));
        //textView.setBackgroundColor(Integer.parseInt("#ffff"));

        */
/*Type specifies who sends it*//*

        if (type == 1) {
            textView.setBackgroundResource(R.drawable.rounded_corner2);
            //lp.setMargins(400, 20, 10, 10);
            lp.gravity = Gravity.RIGHT;
            lp.setMargins(0, 20, 20, 10);
            textView.setLayoutParams(lp);

        } else {

            textView.setBackgroundResource(R.drawable.rounded_corner1);

            lp.setMargins(10, 10, 10, 10);

            textView.setLayoutParams(lp);


            //textView.setBackgroundColor(Color.parseColor("#DCF8C6"));
        }

        layout.addView(textView);
        // scrollView.fullScroll(View.FOCUS_DOWN);

//To start scrolling from down
        scrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        }, 500);


    }

        public void onResume()
        {

            databaseReference.child(UserDetails.username).child("Logged_In").setValue("true");
            super.onResume();

        }
        public void onPause()
        {
            databaseReference.child(UserDetails.username).child("Logged_In").setValue("false");

            super.onPause();
        }


    public void loadImages(String path) {
        File storagePath = new File(Environment.getExternalStorageDirectory(), "DCIM/Chat");
        final File myFile = new File(storagePath, path);
        final String imagePath_Dp = myFile.getAbsolutePath();



        if(myFile.exists())
        {
            try {
               final Context context=this;

                Picasso.with(this).load(myFile)
                        .into(chatDp, new Callback() {
                            @Override
                            public void onSuccess() {
                                Resources res = context.getResources();
                                Bitmap imageBitmap = null;

                                try {
                                    imageBitmap = ((BitmapDrawable) chatDp.getDrawable()).getBitmap();
                                } catch (Exception e) {
                                    //  Toast.makeText(context,"Exception is "+e,Toast.LENGTH_LONG).show();
                                }

                                RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(res, imageBitmap);

                                imageDrawable.setCornerRadius(Math.min(imageDrawable.getMinimumWidth(), imageDrawable.getMinimumHeight()) / 2.0f);

                                chatDp.setImageDrawable(imageDrawable);
                                // Toast.makeText(context, "in picasso" + imagePath_Dp, Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onError() {
                                chatDp.setImageResource(R.drawable.user1);
                            }
                        });

            } catch (Exception e) {
                chatDp.setImageResource(R.drawable.user1);
            }}


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}

*/




import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Chat extends AppCompatActivity {
    LinearLayout layout;
    ImageView sendButton;
    EditText messageArea;
    ScrollView scrollView;

    TextView lastmsg;

    Firebase reference1, reference2;


    DatabaseReference reference11,reference22;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    DatabaseReference lastmessagedb;

    String is_online;
    String logged;
    ImageView chatDp;
    TextView chatName,chatStatus;
    TextView tv;
    String ms[];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);

        layout = (LinearLayout) findViewById(R.id.layout1);
        sendButton = (ImageView) findViewById(R.id.sendButton);
        messageArea = (EditText) findViewById(R.id.messageArea);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        chatDp = (ImageView) findViewById(R.id.chatImageView);
        chatName = (TextView) findViewById(R.id.chatName);
        chatStatus = (TextView) findViewById(R.id.chatStatus);
        lastmsg=(TextView)findViewById(R.id.lastMessagesent);
        Toolbar toolbar = (Toolbar) findViewById(R.id.chattoolbar);

        LayoutInflater inflater = (LayoutInflater)Chat.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View vi = inflater.inflate(R.layout.customisedlistviewadapterlayout, null);
        tv = (TextView)vi.findViewById(R.id.lastMessagesent);



        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("users");
        reference11 = firebaseDatabase.getReference("messages");
        reference22 = firebaseDatabase.getReference("messages");

        lastmessagedb=firebaseDatabase.getReference("messages");
        //calcLastMessage();
        // reference11.child(UserDetails.username).child(UserDetails.username+"_"+UserDetails.chatWith);
        //reference22.child(UserDetails.username).child(UserDetails.chatWith+"_"+UserDetails.username);
        // String is_online=databaseReference.child(UserDetails.username).child("Logged_In").toString();

        // Firebase.setAndroidContext(this);
        //reference1 = new Firebase("https://chatapp-1d3d6.firebaseio.com/messages/" + UserDetails.username + "_" + UserDetails.chatWith);
        //reference2 = new Firebase("https://chatapp-1d3d6.firebaseio.com/messages/" + UserDetails.chatWith + "_" + UserDetails.username);


        final String name_of_chat = com.akhil.akhildixit.chat_app.UserDetails.chatWith.toUpperCase();

        loadImages(com.akhil.akhildixit.chat_app.UserDetails.chatWith + ".jpg");



        databaseReference.child(com.akhil.akhildixit.chat_app.UserDetails.chatWith).child("Logged_In").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {


                logged = dataSnapshot.getValue().toString();
                if (logged.equals("true")) {
                    is_online = "ONLINE";
                    chatName.setText(name_of_chat);
                    chatStatus.setText(is_online);
                    // Chat.this.setTitle(name_of_chat + " "+is_online);

                    Toast.makeText(Chat.this, "In snapshot if " + com.akhil.akhildixit.chat_app.UserDetails.chatWith + logged, Toast.LENGTH_LONG).show();
                } else {


                    databaseReference.child(com.akhil.akhildixit.chat_app.UserDetails.chatWith).child("Last_seen").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {

                            String lastseen;
                            lastseen = dataSnapshot.getValue().toString();
                            if (logged.equals("false")) {
                                chatName.setText(name_of_chat);
                                chatStatus.setText(lastseen);
                                //  Chat.this.setTitle(name_of_chat + " "+lastseen);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                            Toast.makeText(Chat.this, "Error occured in reading data", Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(Chat.this, "Error occured in reading data", Toast.LENGTH_LONG).show();
            }

        });

        /*To add name of the person in title*/

        sendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String messageText = messageArea.getText().toString();
                DateFormat dateFormat = new SimpleDateFormat(" HH:mm a");
                Calendar cal = Calendar.getInstance();
                String time = dateFormat.format(cal.getTime());



                if (!messageText.equals("")) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("message", messageText + "    " + time);
                    map.put("user", com.akhil.akhildixit.chat_app.UserDetails.username);

                    lastmessagedb.child(com.akhil.akhildixit.chat_app.UserDetails.username).child(com.akhil.akhildixit.chat_app.UserDetails.username + "_" + com.akhil.akhildixit.chat_app.UserDetails.chatWith).child("last_msg")
                            .setValue(messageText);


                    reference11.child(com.akhil.akhildixit.chat_app.UserDetails.username).child(com.akhil.akhildixit.chat_app.UserDetails.username + "_" + com.akhil.akhildixit.chat_app.UserDetails.chatWith).child("Messages")
                            .push().setValue(map);
                    reference22.child(com.akhil.akhildixit.chat_app.UserDetails.username).child(com.akhil.akhildixit.chat_app.UserDetails.chatWith + "_" + com.akhil.akhildixit.chat_app.UserDetails.username).child("Messages")
                            .push().setValue(map);
                    messageArea.setText("");

                }

            }
        });
        reference11.child(com.akhil.akhildixit.chat_app.UserDetails.username).child(com.akhil.akhildixit.chat_app.UserDetails.username + "_" + com.akhil.akhildixit.chat_app.UserDetails.chatWith).child("Messages")
                .addChildEventListener(new com.google.firebase.database.ChildEventListener() {
                    @Override
                    public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {


                        Map <String, String> map = (Map)dataSnapshot.getValue();

                        //  Map map = dataSnapshot.getValue(Map.class);
                        String message = map.get("message").toString();
                        String userName = map.get("user").toString();

                        //   Toast.makeText(Chat.this,"Message is - "+message+" Username is - "+userName,Toast.LENGTH_LONG).show();


                        if (userName.equals(com.akhil.akhildixit.chat_app.UserDetails.username)) {
                            // addMessageBox("You:-\n" + message, 1);    If we want to have the name also written
                            addMessageBox(message, 1);
                        } else {
                            //addMessageBox(UserDetails.chatWith + ":-\n" + message, 2);
                            //addMessageBox(message, 2);
                        }

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

                    }
                });



        reference11.child(com.akhil.akhildixit.chat_app.UserDetails.chatWith).child(com.akhil.akhildixit.chat_app.UserDetails.chatWith + "_" + UserDetails.username).child("Messages")
                .addChildEventListener(new com.google.firebase.database.ChildEventListener() {
                    @Override
                    public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {


                        Map <String, String> map = (Map)dataSnapshot.getValue();

                        //  Map map = dataSnapshot.getValue(Map.class);
                        String message = map.get("message").toString();
                        String userName = map.get("user").toString();

                        //   Toast.makeText(Chat.this,"Message is - "+message+" Username is - "+userName,Toast.LENGTH_LONG).show();


                        if (userName.equals(com.akhil.akhildixit.chat_app.UserDetails.chatWith)) {
                            // addMessageBox("You:-\n" + message, 1);    If we want to have the name also written
                            addMessageBox(message, 2);
                        } else {
                            //addMessageBox(UserDetails.chatWith + ":-\n" + message, 2);
                            //addMessageBox(message, 2);
                        }

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

                    }
                });



    }

    public void addMessageBox(String message,int type){
        TextView textView = new TextView(Chat.this);
        textView.setText(message);





        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        lp.setMargins(10, 10, 10, 10);
//        textView.setLayoutParams(lp);


        //textView.setTextColor(Integer.parseInt("#2c3e50"));
        //textView.setBackgroundColor(Integer.parseInt("#ffff"));

        /*Type specifies who sends it*/
        if (type == 1) {
            textView.setBackgroundResource(R.drawable.rounded_corner2);
            //lp.setMargins(400, 20, 10, 10);
            lp.gravity = Gravity.RIGHT;
            lp.setMargins(0, 20, 20, 10);
            textView.setLayoutParams(lp);
            tv.setText(message);

        } else {

            textView.setBackgroundResource(R.drawable.rounded_corner1);

            lp.setMargins(10, 10, 10, 10);

            textView.setLayoutParams(lp);
            tv.setText(message);


            //textView.setBackgroundColor(Color.parseColor("#DCF8C6"));
        }

        layout.addView(textView);
        // scrollView.fullScroll(View.FOCUS_DOWN);

//To start scrolling from down
        scrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        }, 500);


    }


    public void onResume()
    {

        databaseReference.child(com.akhil.akhildixit.chat_app.UserDetails.username).child("Logged_In").setValue("true");
        super.onResume();

    }
    public void onPause()
    {
        databaseReference.child(com.akhil.akhildixit.chat_app.UserDetails.username).child("Logged_In").setValue("false");

        super.onPause();
    }


    public void loadImages(String path) {
        File storagePath = new File(Environment.getExternalStorageDirectory(), "DCIM/Chat");
        final File myFile = new File(storagePath, path);
        final String imagePath_Dp = myFile.getAbsolutePath();



        if(myFile.exists())
        {
            try {
                final Context context=this;

                Picasso.with(this).load(myFile)
                        .into(chatDp, new Callback() {
                            @Override
                            public void onSuccess() {
                                Resources res = context.getResources();
                                Bitmap imageBitmap = null;

                                try {
                                    imageBitmap = ((BitmapDrawable) chatDp.getDrawable()).getBitmap();
                                } catch (Exception e) {
                                    //  Toast.makeText(context,"Exception is "+e,Toast.LENGTH_LONG).show();
                                }

                                RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(res, imageBitmap);

                                imageDrawable.setCornerRadius(Math.min(imageDrawable.getMinimumWidth(), imageDrawable.getMinimumHeight()) / 2.0f);

                                chatDp.setImageDrawable(imageDrawable);
                                // Toast.makeText(context, "in picasso" + imagePath_Dp, Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onError() {
                                chatDp.setImageResource(R.drawable.user1);
                            }
                        });

            } catch (Exception e) {
                chatDp.setImageResource(R.drawable.user1);
            }}


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



    public void calcLastMessage() {


        Toast.makeText(Chat.this,"in cal",Toast.LENGTH_LONG).show();

        Query lq=reference11.child(com.akhil.akhildixit.chat_app.UserDetails.username).child(com.akhil.akhildixit.chat_app.UserDetails.chatWith+"_"+UserDetails.username).orderByKey().limitToLast(1);

        lq.addChildEventListener(new com.google.firebase.database.ChildEventListener() {
            @Override
            public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                String message=dataSnapshot.child("message").getValue().toString();
                ms=message.split("    ");

                tv.setText(ms[0]);
                Intent intent=new Intent(Chat.this, com.akhil.akhildixit.chat_app.CustomiseList.class);
                intent.putExtra("Last",ms[0]);
                Toast.makeText(Chat.this,tv.getText().toString(),Toast.LENGTH_LONG).show();

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

            }
        });


    }


}






