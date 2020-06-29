package com.example.clubscse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {
public EditText uid,name,section,branch,email,contact;
public  static EventDetail eventDetail;
public RequestQueue requestQueue;
public ImageView poster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_registration );
        poster=findViewById( R.id.poster_registration_form );
        uid=findViewById( R.id.uid_registration_edit_text );
        name=findViewById( R.id.name_registration_edit_text );
        section=findViewById( R.id.section_registration_edit_text );
        branch=findViewById( R.id.branch_registration_edit_text );
        email=findViewById( R.id.email_registration_edit_text );
        contact=findViewById( R.id.contactno_registration_edit_text );
        requestQueue= Volley.newRequestQueue(  this);
        Picasso.with( this ).load( eventDetail.getImage_url() ).into( poster );

    }

 public   void  sendData(View view){
   final String url="https://unreaving-power.000webhostapp.com/eventRegistration.php";
      Map<String,String> map=new HashMap<>(  );
      map.put( "uid",""+uid.getText().toString().trim() );
      map.put( "name",""+name.getText().toString().trim() );
      map.put( "email",""+email.getText().toString().trim() );
      map.put( "section",""+section.getText().toString().trim() );
      map.put( "branch",""+branch.getText().toString().trim() );
      map.put( "contact",""+contact.getText().toString().trim() );
      map.put( "ename",""+eventDetail.getTitle() );
      map.put( "club",""+eventDetail.getClub() );
      map.put( "location",""+eventDetail.getLocation() );
      map.put( "imageUrl",""+eventDetail.getImage_url() );
      map.put( "descUrl",""+eventDetail.getDescription_url() );
      map.put( "start_time",""+eventDetail.getStarting_time() );
      map.put( "end_time",""+eventDetail.getEnd_time() );
      map.put( "date",""+eventDetail.getDate() );

      JsonObjectRequest jsonObjectRequest=new JsonObjectRequest( Request.Method.POST, url, new JSONObject( map ), new Response.Listener<JSONObject>() {
          @Override
          public void onResponse(JSONObject response) {
              try {
                  if(response.getInt( "success" )==1){
                      Toast.makeText( Registration.this,"succesfully registered",Toast.LENGTH_LONG ).show();
                      startActivity(  new Intent( Registration.this,navigation_timeline.class ) );
                  }
                  else
                      Toast.makeText( Registration.this,"some error occured",Toast.LENGTH_LONG ).show();
              } catch (JSONException e) {
                  e.printStackTrace();
              }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
              Log.e( "load land",""+error );
          }
      } );
      requestQueue.add( jsonObjectRequest );
    }


public static void getEvent(EventDetail eventDetail0){
        eventDetail=eventDetail0;
}




}
