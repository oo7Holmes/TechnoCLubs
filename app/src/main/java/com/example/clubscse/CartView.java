package com.example.clubscse;

public class CartView {

    private String title;
    private String descUrl;
    private String Imgurl;


    public CartView(String title,String descUrl,String url){
        this.title=title;
        this.descUrl=descUrl;
        this.Imgurl=url;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription_cart(){
        return  descUrl;
    }
    public String getImgUrl(){
        return  Imgurl;
    }
}
