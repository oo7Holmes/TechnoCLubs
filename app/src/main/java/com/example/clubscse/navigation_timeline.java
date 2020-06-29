package com.example.clubscse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class navigation_timeline extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public RecyclerView recyclerView;
    public ArrayList<EventDetail> list_events;
    public ProgressBar progressBar;
    public RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_navigation_timeline );
        progressBar=findViewById( R.id.progress_bar );
        recyclerView=findViewById( R.id.Recycler_view );
        recyclerView.setVisibility( View.GONE );
        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        NavigationView navigationView = findViewById( R.id.nav_view );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener( this );
        requestQueue= Volley.newRequestQueue( this );
        list_events=new ArrayList<>( );
        getEvents();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.cart_menu, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id==R.id.cart_icon){
            startActivity( new Intent( this,Cart.class ) );
            return true;
        }

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected( item );
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch(id){

            case R.id.attended: startActivity( new Intent( this,attended.class ) );
            break;
            case R.id.to_be_attended: startActivity( new Intent( this,to_be_attended.class ) );
            break;
            case R.id.feedback :
                Toast.makeText( this,"Functionality to be added soon ",Toast.LENGTH_LONG ).show();
                break;
            case R.id.settings :
                Toast.makeText( this,"Functionality to be added soon ",Toast.LENGTH_LONG ).show();
                break;
            case R.id.nav_share :
                Toast.makeText( this,"Functionality to be added soon ",Toast.LENGTH_LONG ).show();
                break;
            case R.id.nav_send :
                Toast.makeText( this,"Functionality to be added soon ",Toast.LENGTH_LONG ).show();
                break; }


        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }


    public void getEvents(){
        final String Events_url="https://unreaving-power.000webhostapp.com/getEvents.php";
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest( Request.Method.POST, Events_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    insertEvents(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error","aagya");
            }
        }){
            @Override
            public Map<String, String> getHeaders()  {
                HashMap<String,String> headers=new HashMap<String, String>();
                headers.put("Content-Type","application/json; charset=utf-8");
                return headers;
            }
        };
        requestQueue.add( jsonArrayRequest );
    }


    public void insertEvents(JSONArray response) throws JSONException {
        int i=0;
        while(i<response.length()){
            JSONObject jsonObject= response.getJSONObject( i );
            list_events.add( new EventDetail( jsonObject.getInt( "eid" ),jsonObject.getString( "ename" ),jsonObject.getString( "club" ),jsonObject.getString( "location" ),jsonObject.getInt( "seats" ),jsonObject.getString( "imageUrl" ),jsonObject.getString("descUrl" ),jsonObject.getString( "date_events" ),jsonObject.getString( "start_time" ),jsonObject.getString( "end_time" ) ));
            i++;}
        progressBar.setVisibility( View.GONE );
        recyclerView.setVisibility( View.VISIBLE );
        WordList worldList = new WordList(list_events,navigation_timeline.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(navigation_timeline.this));
        recyclerView.setAdapter(worldList);
    }
}



