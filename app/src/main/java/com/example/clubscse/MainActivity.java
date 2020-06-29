package com.example.clubscse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText uid_main,password_main;

    RequestQueue requestQueue_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);
        uid_main=findViewById(R.id.uid);
        password_main=findViewById(R.id.password);
        requestQueue_main=  Volley.newRequestQueue(this);
    }
    public void login_students(View v){
        Map<String,String> object_main =new HashMap<>();
        object_main.put("uid",uid_main.getText().toString().trim());
        object_main.put("password",password_main.getText().toString().trim());
        JSONObject jsonObject_main=new JSONObject(object_main);
        final String URL_main="https://unreaving-power.000webhostapp.com/login.php";
        JsonObjectRequest jsonObjectRequest_main=new JsonObjectRequest(Request.Method.POST, URL_main, jsonObject_main, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                int success= 0;
                try {
                    success = response.getInt("success");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(success==1){
                    Intent intent=new Intent(MainActivity.this, navigation_timeline.class );
                    startActivity(intent);
                    finish();
                }
                else
                    Toast.makeText(MainActivity.this,"Invalid Details",Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders()  {
                HashMap<String,String> headers=new HashMap<String, String>();
                headers.put("Content-Type","application/json; charset=utf-8");
                return headers;
            }
        };
        requestQueue_main.add(jsonObjectRequest_main);
    }

    public void signup(View view) {
        Intent intent=new Intent(this, Signup.class);
        startActivity(intent);
    }


}
