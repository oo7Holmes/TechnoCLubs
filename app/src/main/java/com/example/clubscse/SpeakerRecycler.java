package com.example.clubscse;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SpeakerRecycler extends  RecyclerView.Adapter<SpeakerRecycler.SpeakerViewHolder>  {
  public ArrayList<SpeakerDetail> list;
   public Context context;
public RequestQueue requestQueue;

   SpeakerRecycler(Context context,ArrayList<SpeakerDetail> list){
       this.list=list;
       this.context=context;
   requestQueue= Volley.newRequestQueue( context );
   }
    @NonNull
    @Override
    public SpeakerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate( R.layout.custom_speaker_layout,viewGroup,false );
        return new SpeakerRecycler.SpeakerViewHolder( view ); }

    @Override
    public void onBindViewHolder(@NonNull SpeakerViewHolder speakerViewHolder, int i) {
        Picasso.with( context ).load(list.get( i ).imageUrl  ).into( speakerViewHolder.speaker_image );
        speakerViewHolder.speaker_name.setText( list.get( i ).getSname() );
        speakerViewHolder.rating.setText( list.get( i ).getRating() );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SpeakerViewHolder extends RecyclerView.ViewHolder {
       public ImageView speaker_image;
       public TextView speaker_name;
       public TextView rating;

       public SpeakerViewHolder(@NonNull View itemView) {
            super( itemView );
           speaker_image= itemView.findViewById( R.id.speaker_image );
           speaker_name=itemView.findViewById( R.id.speaker_name );
           rating=itemView.findViewById( R.id.speaker_rating );
        }
    }
}
