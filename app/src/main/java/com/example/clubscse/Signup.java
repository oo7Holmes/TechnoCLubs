package com.example.clubscse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

public class Signup extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {
    private EditText name,uid,mail,password;
    private Spinner spinner;
     private String University[]={"Chandigarh University","LPU","Chitkara"};
     private Map<String,String> object =new HashMap<>();
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        name=findViewById( R.id.name_signup);
        uid=findViewById(R.id.uid_signup);
        mail=findViewById(R.id.mail_signup);
        password=findViewById(R.id.password_signup);
        spinner=findViewById( R.id.spinner );
        requestQueue=  Volley.newRequestQueue(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,University);
        spinner.setAdapter( aa);
    }
    public void signup_students(View v){

        object.put("uid",uid.getText().toString().trim());
        object.put("name",name.getText().toString().trim());
        object.put("mail",mail.getText().toString().trim());
        object.put("password",password.getText().toString().trim());
        object.put( "university","Chandigarh University" );
        JSONObject jsonObject=new JSONObject(object);
        final String URL="https://unreaving-power.000webhostapp.com/insertData.php";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                int success= 0;
                try {
                    success = response.getInt("success");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(success==1){
                    Toast.makeText( Signup.this, "succesfully created account", Toast.LENGTH_SHORT ).show();
                    Intent intent=new Intent(Signup.this, MainActivity.class );
                    startActivity(intent);
                }
                else
                    Toast.makeText(Signup.this,"Invalid Details",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("signup_button","aaya fuck"+error);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String,String> headers=new HashMap<String, String>();
                headers.put("Content-Type","application/json; charset=utf-8");
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }
    //Performing action onItemSelected and onNothing selected
  @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
      // object.put( "university",University[position] );
        //Log.e("spinner",""+object.get( "university" )
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

