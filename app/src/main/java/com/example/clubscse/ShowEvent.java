package com.example.clubscse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShowEvent extends AppCompatActivity {
public  static EventDetail eventDetail;
public RecyclerView recyclerView;
public final int noOfColoumn=2;
public static ArrayList<SpeakerDetail> speaker_list;
public TextView club_name;
public TextView title;
public TextView description;
public ImageView event_image;
public RequestQueue requestQueue;
public Button registration;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_show_event );
        description=findViewById( R.id.description_show_event );
        event_image=findViewById( R.id.event_image );
        requestQueue=Volley.newRequestQueue( this );
        recyclerView=findViewById( R.id.show_event_recycler_view );
        club_name=findViewById( R.id.club_name );
        title=findViewById( R.id.title_show_event );
        registration=findViewById( R.id.registration_button );
        registration.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registration.getEvent( eventDetail );
                startActivity( new Intent( ShowEvent.this,Registration.class ) );
            }
        } );
        club_name.setText( eventDetail.getClub() );
        title.setText( eventDetail.getTitle() );

        getDescription(eventDetail.getDescription_url());
        getImage(eventDetail.getImage_url());

    SpeakerRecycler speakerRecycler=new SpeakerRecycler( this,speaker_list );
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager( this, LinearLayoutManager.HORIZONTAL,false ) );
    recyclerView.setAdapter(speakerRecycler);
    }

    private void getImage(String image_url) {
        Picasso.with( this ).load( image_url ).into( event_image );
    }

    private void getDescription(String description_url) {
        StringRequest stringRequest=new StringRequest( Request.Method.POST, description_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
             description.setText( response );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } );
        requestQueue.add( stringRequest );

    }

    public static void getDetail(EventDetail event,ArrayList<SpeakerDetail> speaker){
     eventDetail=event;
     speaker_list=speaker;
}

}
