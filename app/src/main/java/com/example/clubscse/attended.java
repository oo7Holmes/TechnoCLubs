package com.example.clubscse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class attended extends AppCompatActivity {
    public RequestQueue requestQueue;
    public ProgressBar progressBar;
    public RecyclerView recyclerView;
    public ArrayList<EventDetail> list=new ArrayList<>(  );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_attended );
        requestQueue= Volley.newRequestQueue( this );
        progressBar=findViewById( R.id.attended_progressbar );
        recyclerView=findViewById( R.id.attended_recycler_view );
        recyclerView.setVisibility( View.GONE );
        getdata();
    }


    void getdata(){
        Map<String,String> map=new HashMap<>(  );
        map.put( "uid","17bcs1153" );
        final String url="https://unreaving-power.000webhostapp.com/Attended.php";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest( Request.Method.POST, url, new JSONObject( map ), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getInt( "success" )==0){
                        Toast.makeText( attended.this,"Some error occured",Toast.LENGTH_LONG ).show();
                    }
                    else
                    {
                        JSONArray jsonArray=response.getJSONArray( "message" );
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject= jsonArray.getJSONObject( i );
                            list.add( new EventDetail( jsonObject.getInt( "eid" ),jsonObject.getString( "ename" ),jsonObject.getString( "club" ),jsonObject.getString( "location" ),0,jsonObject.getString( "imageUrl" ),jsonObject.getString( "descUrl" ),jsonObject.getString( "event_date" ),jsonObject.getString( "start_time" ),jsonObject.getString( "end_time" ) ) );
                        }
                        progressBar.setVisibility( View.GONE );
                        recyclerView.setVisibility( View.VISIBLE );
                        toBeAttendedRecycler toBeAttendedRecycler= new toBeAttendedRecycler(list,attended.this);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(attended.this));
                        recyclerView.setAdapter(toBeAttendedRecycler);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } );
        requestQueue.add( jsonObjectRequest );
    }
}
