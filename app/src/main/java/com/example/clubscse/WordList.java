package com.example.clubscse;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class WordList extends RecyclerView.Adapter<WordList.WorldViewHolder> {
   public ArrayList<EventDetail> list_events;
  public   Context context;
  public  RequestQueue requestQueue_word;
  public ArrayList<SpeakerDetail> speaker_list;
    public WordList( ArrayList<EventDetail> list_events ,Context context ){
        this.list_events=list_events;
        this.context=context;
        requestQueue_word=  Volley.newRequestQueue(context);
    }
    @NonNull
    @Override
    public WorldViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate( R.layout.base_layout,viewGroup,false );
        return new WorldViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull final WorldViewHolder worldViewHolder, final int i) {
        worldViewHolder.clear.setVisibility( View.GONE );
        worldViewHolder.title.setText( list_events.get( i ).getTitle() );
     String descUrl=list_events.get( i ).getDescription_url();
     Log.v("descUrl",list_events.get( i ).getDescription_url());
     StringRequest stringRequest=new StringRequest( Request.Method.POST, descUrl, new Response.Listener<String>() {
         @Override
         public void onResponse(String response) {
             worldViewHolder.description.setText( response );
         }
     }, new Response.ErrorListener() {
         @Override
         public void onErrorResponse(VolleyError error) {
             Log.v( "error in WorldList","error in inflating description view" );
         }
     } );
 requestQueue_word.add( stringRequest );
        Log.v("imgUrl",list_events.get( i ).getImage_url());
 Picasso.with( context ).load( list_events.get( i ).getImage_url() ).into( worldViewHolder.imageView );
 worldViewHolder.cardView.setOnClickListener( new View.OnClickListener() {
         @Override
         public void onClick(View v) {
         diaplayEvent(list_events.get( i ).getTitle(),i);

         }
     } );
 worldViewHolder.interested.setOnClickListener( new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         Cart.interested_events( list_events.get( i ));
         Toast.makeText( context,"Item is added to cart ",Toast.LENGTH_LONG ).show();
     }
 } );
    }

    private void diaplayEvent(String title, final int i) {
       speaker_list=new ArrayList<>(  );
        Map<String,String> map=new HashMap<>(  );
        map.put( "ename",""+title );
        final String URL="https://unreaving-power.000webhostapp.com/getSpeakerImage.php";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest( Request.Method.POST, URL, new JSONObject( map ), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray( "speaker" );
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject( i );
                        speaker_list.add( new SpeakerDetail( jsonObject.getString( "sname" ),jsonObject.getString( "rating" ),jsonObject.getString( "spkr_img" ) ) );

                    }
                    ShowEvent.getDetail( list_events.get( i ),speaker_list );
                    context.startActivity( new Intent( context,ShowEvent.class ) );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } );
      requestQueue_word.add( jsonObjectRequest );
    }


    @Override
    public int getItemCount() {
        return list_events.size();
    }

    public class WorldViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView imageView;
        public TextView description;
        public  TextView interested;
        public CardView cardView;
        public TextView clear;

        public WorldViewHolder(@NonNull View itemView) {
            super( itemView );
            title = itemView.findViewById( R.id.title_base_layout );
            imageView=itemView.findViewById( R.id.image_view_base_layout );
            description=itemView.findViewById( R.id.discription_base_layout );
            interested=itemView.findViewById( R.id.interested_base_layout );
            cardView=itemView.findViewById( R.id.card_view );
            clear=itemView.findViewById( R.id.clear_base_layout );
        }

    }
}
