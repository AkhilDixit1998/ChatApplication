package com.akhil.akhildixit.chat_app;

/**
 * Created by Akhil Dixit on 6/27/2017.
 */

import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
public class Login extends AppCompatActivity {
    TextView register;
    EditText username, password;
    Button loginButton;
    String user, pass;

    MarshMallowPermission marshMallowPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = (TextView)findViewById(R.id.register);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        loginButton = (Button)findViewById(R.id.loginButton);

        marshMallowPermission=new MarshMallowPermission(this);
        marshMallowPermission.requestPermissionForExternalStorage();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                pass = password.getText().toString();

                if(user.equals("")){
                    username.setError("Can't Be Blank");
                }
                else if(pass.equals("")){
                    password.setError("Can't Be Blank");
                }
                else{
                    String url = "https://chatapp-1d3d6.firebaseio.com/users.json";
                    final ProgressDialog pd = new ProgressDialog(Login.this);
                    pd.setMessage("Loading...");
                    pd.show();

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                        @Override
                        public void onResponse(String s) {
                            if(s.equals("null")){
                                Toast.makeText(Login.this, "Sorry User Not Found", Toast.LENGTH_LONG).show();
                            }
                            else{
                                try {
                                    JSONObject obj = new JSONObject(s);

                                    if(!obj.has(user)){
                                        Toast.makeText(Login.this, "Sorry User Not Found", Toast.LENGTH_LONG).show();
                                    }
                                    else if(obj.getJSONObject(user).getString("password").equals(pass)){
                                        UserDetails.username = user;
                                        UserDetails.password = pass;
                                        marshMallowPermission.requestPermissionForExternalStorage();

                                        startActivity(new Intent(Login.this, Users.class));
                                    }
                                    else {
                                        Toast.makeText(Login.this, "INCORRECT PASSWORD", Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            pd.dismiss();
                        }
                    },new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            System.out.println("" + volleyError);
                            pd.dismiss();
                        }
                    });

                    RequestQueue rQueue = Volley.newRequestQueue(Login.this);
                    rQueue.add(request);
                }

            }
        });
    }
}*/










/*New Trying Giving online feature*/









public class Login extends AppCompatActivity {
    TextView register;
    EditText username, password;
    Button loginButton;
    String user, pass;

    DatabaseReference databaseReference,databaseReferencenotifications;
    FirebaseDatabase database;


    MarshMallowPermission marshMallowPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);


        database = FirebaseDatabase.getInstance();
        databaseReference=database.getReference("users");

        register = (TextView)findViewById(R.id.register);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        loginButton = (Button)findViewById(R.id.loginButton);

        marshMallowPermission=new MarshMallowPermission(this);
        marshMallowPermission.requestPermissionForExternalStorage();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                pass = password.getText().toString();

                if(user.equals("")){
                    username.setError("Can't Be Blank");
                }
                else if(pass.equals("")){
                    password.setError("Can't Be Blank");
                }
                else{
                    String url = "https://chatapp-1d3d6.firebaseio.com/users.json";
                    final ProgressDialog pd = new ProgressDialog(Login.this);
                    pd.setMessage("Loading...");
                    pd.show();

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                        @Override
                        public void onResponse(String s) {
                            if(s.equals("null")){
                                Toast.makeText(Login.this, "Sorry User Not Found", Toast.LENGTH_LONG).show();
                            }
                            else{
                                try {
                                    JSONObject obj = new JSONObject(s);

                                    if(!obj.has(user)){
                                        Toast.makeText(Login.this, "Sorry User Not Found", Toast.LENGTH_LONG).show();
                                    }
                                    else if(obj.getJSONObject(user).getString("password").equals(pass)){
                                        UserDetails.username = user;
                                        UserDetails.password = pass;

/*Here i give logged in feature*/

                                        DateFormat dateFormat = new SimpleDateFormat(" HH:mm a");
                                        Calendar cal = Calendar.getInstance();
                                        String time=dateFormat.format(cal.getTime());

                                        databaseReference.child(user).child("Logged_In").setValue("true");
                                        databaseReference.child(user).child("Last_seen").setValue(time);
                                        marshMallowPermission.requestPermissionForExternalStorage();

                                        notifications();

                                        startActivity(new Intent(Login.this, Users.class));
                                    }
                                    else {
                                        Toast.makeText(Login.this, "INCORRECT PASSWORD", Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            pd.dismiss();
                        }
                    },new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            System.out.println("" + volleyError);
                            pd.dismiss();
                        }
                    });

                    RequestQueue rQueue = Volley.newRequestQueue(Login.this);
                    rQueue.add(request);
                }

            }
        });
    }


    public void notifications() {
        databaseReferencenotifications = database.getReference();

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
       /* databaseReferencenotifications.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setContentTitle("New notificatins");
                builder.setContentText("Hello this is a test Firebase notification, a new database child has been added");
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(1, builder.build());
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
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/


       db.child("messages").child(UserDetails.username).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setContentTitle("New notificatins");
                builder.setContentText(dataSnapshot.getValue().toString());
                builder.setStyle(new NotificationCompat.BigTextStyle().bigText(dataSnapshot.getValue().toString()));
                Toast.makeText(Login.this,dataSnapshot.getValue().toString(),Toast.LENGTH_LONG).show();
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(1, builder.build());
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
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}


