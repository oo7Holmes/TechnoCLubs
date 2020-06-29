package com.example.clubscse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
static ArrayList<EventDetail> list=new ArrayList<>(  );;
static public  RecyclerView recyclerView;
static public   TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_cart );
        textView=findViewById( R.id.no_item_in_cart );
        textView.setVisibility( View.GONE );
        recyclerView=findViewById( R.id.cart_recycler_view );
       CartRecycler cartView=new CartRecycler( this,list );
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       recyclerView.setAdapter( cartView );

    }

    public static void interested_events(EventDetail eventDetail){
     list.add(eventDetail);
    }

}
