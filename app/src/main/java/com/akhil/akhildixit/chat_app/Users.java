package com.akhil.akhildixit.chat_app;

/**
 * Created by Akhil Dixit on 6/27/2017.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/*
public class Users extends AppCompatActivity {
    ListView usersList;
    TextView noUsersText;
    ArrayList<String> al = new ArrayList<>();
    int totalUsers = 0;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        usersList = (ListView)findViewById(R.id.usersList);
        noUsersText = (TextView)findViewById(R.id.noUsersText);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("messages");

        pd = new ProgressDialog(Users.this);
        pd.setMessage("Loading...");
        pd.show();

        String url = "https://chatapp-1d3d6.firebaseio.com/users.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                doOnSuccess(s);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(Users.this);
        rQueue.add(request);

        //usersList.setAdapter(new CustomiseList(Users.this,UserDetails.chatWith));

        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserDetails.chatWith = al.get(position);
                startActivity(new Intent(Users.this, Chat.class));
            }
        });
    }

    public void doOnSuccess(String s){
        try {
            JSONObject obj = new JSONObject(s);

            Iterator i = obj.keys();
            String key = "";

            while(i.hasNext()){
                key = i.next().toString();

                if(!key.equals(UserDetails.username)) {
                    al.add(key);
                }

                totalUsers++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(totalUsers <=1){
           // noUsersText.setVisibility(View.VISIBLE);
           // usersList.setVisibility(View.GONE);
        }
        else{
          //  noUsersText.setVisibility(View.GONE);
            usersList.setVisibility(View.VISIBLE);
           // Toast.makeText(this,"here",Toast.LENGTH_LONG).show();
           // usersList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al));
            usersList.setAdapter(new CustomiseList(Users.this,al));
        }

        pd.dismiss();
    }
}*/













/*public class Users extends AppCompatActivity {
    ListView usersList;
    TextView noUsersText;
    ArrayList<String> al = new ArrayList<>();
    int totalUsers = 0;
    ProgressDialog pd;
    StorageReference riversRef;
    MarshMallowPermission marshMallowPermission;

    private StorageReference mStorageRef;

    String imagePath_Dp="";

    String filespath;
    String filepath;

    Intent intent_chat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        usersList = (ListView)findViewById(R.id.usersList);
        noUsersText = (TextView)findViewById(R.id.noUsersText);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("messages");


        marshMallowPermission = new MarshMallowPermission(Users.this);

        marshMallowPermission.requestPermissionForExternalStorage();

        filespath= Environment.DIRECTORY_DCIM;
        filepath="/storage/emulated/0/DCIM/Camera/IMG_20170624_160423.jpg";





        *//*Uplaoding here*//*
        upload_files();

        pd = new ProgressDialog(Users.this);
      //  pd.setMessage("Loading...");
        pd.show();

        String url = "https://chatapp-1d3d6.firebaseio.com/users.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                doOnSuccess(s);
                marshMallowPermission.requestPermissionForExternalStorage();

            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(Users.this);
        rQueue.add(request);

        //usersList.setAdapter(new CustomiseList(Users.this,UserDetails.chatWith));

        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserDetails.chatWith = al.get(position);
                marshMallowPermission.requestPermissionForExternalStorage();
                startActivity(new Intent(Users.this, Chat.class));

            }
        });
    }

    public void doOnSuccess(String s){
        try {
            marshMallowPermission.requestPermissionForExternalStorage();
            JSONObject obj = new JSONObject(s);

            Iterator i = obj.keys();
            String key = "";

            while(i.hasNext()){
                key = i.next().toString();

                if(!key.equals(UserDetails.username)) {
                    al.add(key);
                }

                totalUsers++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(totalUsers <=1){
            // noUsersText.setVisibility(View.VISIBLE);
            // usersList.setVisibility(View.GONE);
        }
        else{
            //  noUsersText.setVisibility(View.GONE);
            usersList.setVisibility(View.VISIBLE);
            // Toast.makeText(this,"here",Toast.LENGTH_LONG).show();
            // usersList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al));
            usersList.setAdapter(new CustomiseList(Users.this,al));
            marshMallowPermission.requestPermissionForExternalStorage();
        }

        pd.dismiss();
    }


    public void upload_files()
    {
        marshMallowPermission.requestPermissionForExternalStorage();
        Uri file = Uri.fromFile(new File(filepath));
         riversRef = mStorageRef.child("images");

        riversRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        downlaod_files();
                        Log.d("Debug","Succes files");

                        Intent intent=new Intent();
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

    *//*public void downlaod_files()
    {


        try {
            final File localFile = File.createTempFile("chatappfiles", "created");

            riversRef.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            // Successfully downloaded data to local file
                            // ...
                            Toast.makeText(Users.this,"download pass: "+localFile.getAbsolutePath(),Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle failed download
                    // ...
                }
            });

        }
        catch (Exception e)
        {
            Toast.makeText(this,"download failed",Toast.LENGTH_LONG).show();
        }





    }*//*

    public void downlaod_files()
    {
        File storagePath = new File(Environment.getExternalStorageDirectory(), "DCIM");
// Create direcorty if not exists
        if(!storagePath.exists()) {
            storagePath.mkdirs();
        }

        final File myFile = new File(storagePath,"chatapp");

        riversRef.getFile(myFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                // Local temp file has been created
                Toast.makeText(Users.this,"Saved :"+myFile.getAbsolutePath(),Toast.LENGTH_LONG).show();
                imagePath_Dp=myFile.getAbsolutePath();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }
}*/











public class Users extends AppCompatActivity {
    ListView usersList;
    TextView noUsersText;
    ArrayList<String> al = new ArrayList<>();

    ArrayList<HashMap<String, String>> almap = new ArrayList<>();
    HashMap<String, String> hashMap = new HashMap<>();

    ArrayList<HashMap<String, String>> lastmsg = new ArrayList<>();
    HashMap<String, String> lastmsgh = new HashMap<>();

    int totalUsers = 0;
    ProgressDialog pd;

    MarshMallowPermission marshMallowPermission;

    private StorageReference mStorageRef;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    String filespath;
    DatabaseReference myRef;
    String ms[];

    LinearLayout layout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        usersList = (ListView) findViewById(R.id.usersList);
        noUsersText = (TextView) findViewById(R.id.noUsersText);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("users");

        myRef = firebaseDatabase.getReference("messages");



        marshMallowPermission = new MarshMallowPermission(Users.this);

        marshMallowPermission.requestPermissionForExternalStorage();

        filespath = Environment.DIRECTORY_DCIM;
        // filepath="/storage/emulated/0/DCIM/Camera/IMG_20170624_160423.jpg";

        Intent inte=new Intent();
        String m= inte.getStringExtra("Last message");
        Toast.makeText(this,m,Toast.LENGTH_LONG);





        /*Uplaoding here*/
        // upload_files();
        //downlaod_files();

        pd = new ProgressDialog(Users.this);
        //  pd.setMessage("Loading...");
        pd.show();

        String url = "https://chatapp-1d3d6.firebaseio.com/users.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                doOnSuccess(s);
                marshMallowPermission.requestPermissionForExternalStorage();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(Users.this);
        rQueue.add(request);

        //usersList.setAdapter(new CustomiseList(Users.this,UserDetails.chatWith));

        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserDetails.chatWith = al.get(position);
                marshMallowPermission.requestPermissionForExternalStorage();
                startActivity(new Intent(Users.this, Chat.class));

            }
        });
    }

    public void doOnSuccess(String s) {
        try {
            marshMallowPermission.requestPermissionForExternalStorage();
            JSONObject obj = new JSONObject(s);

            Iterator i = obj.keys();
            String key = "";

            while (i.hasNext()) {
                key = i.next().toString();

                if (!key.equals(UserDetails.username))
                {


                    //hashMap.put(UserDetails.username,imagePath_Dp);
                    hashMap.put(key,key+".jpg");
                    al.add(key);
                    almap.add(hashMap);
                    //calcLastMessage(key);


                }

                totalUsers++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (totalUsers <= 1) {
             noUsersText.setVisibility(View.VISIBLE);
            // usersList.setVisibility(View.GONE);
        } else {
            //  noUsersText.setVisibility(View.GONE);
            usersList.setVisibility(View.VISIBLE);
            // Toast.makeText(this,"here",Toast.LENGTH_LONG).show();
            // usersList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al));
            // usersList.setAdapter(new CustomiseList(Users.this,al));
            usersList.setAdapter(new CustomiseList(Users.this, almap, al));
            usersList.setLongClickable(true);


            /*To provide long click*/
            usersList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    Toast.makeText(Users.this,"In long pressed",Toast.LENGTH_LONG).show();
                  LayoutInflater  inflater = (LayoutInflater) Users.this.getSystemService(Users.this.LAYOUT_INFLATER_SERVICE);

                   // View view1 = inflater.inflate(R.layout.customisedlistviewadapterlayout, null);





                   ListView listView=(ListView)findViewById(R.id.usersList);
                  //  listView.setAlpha((float) 0.3);
                   // listView.setBackgroundColor(Color.DKGRAY);

//                    layout.getForeground().setAlpha( 0);


                    ImageView imageView=(ImageView)view.findViewById(R.id.imageView);
                    ImagePopup imagePopup=new ImagePopup(Users.this);
                    imagePopup.setWindowHeight(500); // Optional
                    imagePopup.setWindowWidth(600); // Optional
                    imagePopup.setBackgroundColor(Color.TRANSPARENT);  // Optional
                    imagePopup.setHideCloseIcon(true);  // Optional
                    imagePopup.setImageOnClickClose(true);  // Optional
                    imagePopup.initiatePopup(imageView.getDrawable());



                    return false;
                }
            });

            marshMallowPermission.requestPermissionForExternalStorage();
        }

        pd.dismiss();
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




    /*To display the three dots of menu*/
    Menu menu;
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //Intent intent=new Intent(this,Settings.class);
            // startActivity(intent);
            return true;
        } else if (id == R.id.action_newbroadcast) {
            return true;
        }
        else if (id == R.id.action_newgroup) {
            return true;
        }
        else if (id == R.id.action_Starredmessages) {
            return true;
        }
        else if (id == R.id.action_whatsappweb) {
            return true;
        }
        else if(id==R.id.action_uploadpic)
        {
            Intent intent=new Intent(this,Upload_pic.class);
            intent.putExtra("Username",UserDetails.username);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

    private void hideOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void showOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }





    public void calcLastMessage(String key) {


        Toast.makeText(Users.this,"in cal",Toast.LENGTH_LONG).show();

        Query lq=myRef.child(key).child(UserDetails.chatWith+"_"+key).orderByKey().limitToLast(1);

        lq.addChildEventListener(new com.google.firebase.database.ChildEventListener() {
            @Override
            public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                String message=dataSnapshot.child("message").getValue().toString();
                ms=message.split("    ");





                //    tv.setText(ms[0]);
                Toast.makeText(Users.this,ms[0],Toast.LENGTH_LONG).show();

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

















