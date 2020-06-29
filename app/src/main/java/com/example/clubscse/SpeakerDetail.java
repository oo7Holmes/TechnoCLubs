package com.example.clubscse;

public class SpeakerDetail {

    public String sname;
    public String rating;
    public String imageUrl;
    SpeakerDetail(String sname,String rating,String imageUrl){
        this.sname=sname;
        this.rating=rating;
        this.imageUrl=imageUrl;
    }

    public String getSname() {
        return sname;
    }
    public String getRating(){ return rating;}
    public String getImageUrl(){ return imageUrl;}
}
