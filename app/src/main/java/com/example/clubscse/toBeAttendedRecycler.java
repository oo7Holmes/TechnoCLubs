package com.example.clubscse;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class toBeAttendedRecycler extends RecyclerView.Adapter<toBeAttendedRecycler.toBeAttendedHolder>  {
ArrayList<EventDetail> list;
Context context;
    public RequestQueue requestQueue;
    toBeAttendedRecycler(ArrayList<EventDetail> list,Context context ){
    this.list=list;
    this.context=context;
    requestQueue= Volley.newRequestQueue( context );
    }


    @NonNull
    @Override
    public toBeAttendedRecycler.toBeAttendedHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater= LayoutInflater.from(viewGroup.getContext());
        View itemView=layoutInflater.inflate( R.layout.base_layout,viewGroup,false );
        return  new toBeAttendedRecycler.toBeAttendedHolder( itemView );
    }

    @Override
    public void onBindViewHolder(@NonNull final toBeAttendedRecycler.toBeAttendedHolder toBeAttendedHolder, int i) {
             toBeAttendedHolder.title_to_be_attended.setText( list.get( i ).getTitle() );
        Picasso.with( context ).load( list.get( i ).getImage_url() ).into( toBeAttendedHolder.poster_to_be_attended );
        final String url_desc=list.get( i ).getDescription_url();
        StringRequest stringRequest= new StringRequest( Request.Method.POST, url_desc, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                toBeAttendedHolder.Description.setText( response );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } );
        requestQueue.add( stringRequest );
        toBeAttendedHolder.clear.setVisibility( View.GONE );
        toBeAttendedHolder.interested.setVisibility( View.GONE );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class toBeAttendedHolder extends RecyclerView.ViewHolder {
       public TextView title_to_be_attended;
       public TextView Description;
       public ImageView poster_to_be_attended;
       public TextView interested;
       public TextView clear;


        public toBeAttendedHolder(@NonNull View itemView) {
            super( itemView );
            title_to_be_attended=itemView.findViewById( R.id.title_base_layout);
            Description=itemView.findViewById( R.id.discription_base_layout );
            poster_to_be_attended=itemView.findViewById( R.id.image_view_base_layout );
            interested=itemView.findViewById( R.id.interested_base_layout );
            clear=itemView.findViewById( R.id.clear_base_layout );
        }
    }
}
