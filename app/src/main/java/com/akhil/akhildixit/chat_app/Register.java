package com.akhil.akhildixit.chat_app;

/**
 * Created by Akhil Dixit on 6/27/2017.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;


public class Register extends AppCompatActivity {
    EditText username, password;
    Button registerButton;
    String user, pass;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        registerButton = (Button)findViewById(R.id.registerButton);
        login = (TextView)findViewById(R.id.login);
        final DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        Firebase.setAndroidContext(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                pass = password.getText().toString();

                if(user.equals("")){
                    username.setError("Can't Be Blank");
                }
                else if(pass.equals("")){
                    password.setError("Can't Be Blank");
/*Sets with an error*/

                }
                else if(!user.matches("[A-Za-z0-9]+")){
                    username.setError("Only Alphabet or Number Allowed");
                }
                else if(user.length()<5){
                    username.setError("At Least 5 Characters Long");
                }
                else if(pass.length()<5){
                    password.setError("At Least 5 Characters Long");
                }
                else {
                    final ProgressDialog pd = new ProgressDialog(Register.this);
                    pd.setMessage("Loading...");
                    pd.show();

                    String url = "https://chatapp-1d3d6.firebaseio.com/users.json";

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                        @Override
                        public void onResponse(String s) {
                            //Firebase reference = new Firebase("https://chatapp-1d3d6.firebaseio.com/users");

                            if(s.equals("null")) {
                                //reference.child(user).child("password").setValue(pass);
                                database.child("users").child(user).child("password").setValue(pass);
                                Toast.makeText(Register.this, "registration successful", Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                try {
                                    JSONObject obj = new JSONObject(s);

                                    if (!obj.has(user)) {
                                        //reference.child(user).child("password").setValue(pass);
                                        database.child("users").child(user).child("password").setValue(pass);
                                        Toast.makeText(Register.this, "registration successful", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(Register.this, "username already exists", Toast.LENGTH_LONG).show();
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
                            System.out.println("" + volleyError );
                            pd.dismiss();
                        }
                    });

                    RequestQueue rQueue = Volley.newRequestQueue(Register.this);
                    rQueue.add(request);
                }
            }
        });
    }
}





