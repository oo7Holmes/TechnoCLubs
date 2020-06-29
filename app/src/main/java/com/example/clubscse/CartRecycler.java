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


public class CartRecycler extends RecyclerView.Adapter<CartRecycler.CartViewHolder> {
private ArrayList<EventDetail>list_cart;
private Context context;
public RequestQueue requestQueue_cart;

   public CartRecycler(Context context, ArrayList<EventDetail> list){
    this.list_cart=list;
    this.context=context;
    requestQueue_cart= Volley.newRequestQueue( context );
   }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater= LayoutInflater.from(viewGroup.getContext());
        View itemView=layoutInflater.inflate( R.layout.base_layout,viewGroup,false );
       return  new CartViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder cartViewHolder, final int i) {
    cartViewHolder.title_cart.setText( list_cart.get( i ).getTitle() );
    final String url_desc_cart=list_cart.get( i ).description_url;
        StringRequest stringRequest_cart= new StringRequest( Request.Method.POST, url_desc_cart, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              cartViewHolder.description_cart.setText( response );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } );
        requestQueue_cart.add( stringRequest_cart );
        final String url_img_cart=list_cart.get( i ).image_url;
        Picasso.with( context ).load( url_img_cart ).into( cartViewHolder.imageView_cart );
        cartViewHolder.interested_cart.setVisibility( View.GONE );
        cartViewHolder.clear_cart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_cart.remove( i );
                CartRecycler cartRecycler = new CartRecycler( context, list_cart );
                Cart.recyclerView.setAdapter( cartRecycler );

            }
        } );
   }

    @Override
    public int getItemCount() {
        return list_cart.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
     public TextView title_cart;
       public TextView description_cart;
       public ImageView imageView_cart;
       public TextView interested_cart;
       public TextView clear_cart;
        public CartViewHolder(@NonNull View itemView) {
            super( itemView );
            title_cart=itemView.findViewById( R.id.title_base_layout );
            description_cart=itemView.findViewById( R.id.discription_base_layout );
            imageView_cart=itemView.findViewById( R.id.image_view_base_layout );
            interested_cart=itemView.findViewById( R.id.interested_base_layout );
            clear_cart=itemView.findViewById( R.id.clear_base_layout );
        }
    }
}
