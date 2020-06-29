package com.example.clubscse;

public class EventDetail  {
public String title;
public int eid;
public String description_url;
    public String image_url;
    public String club;
    public String Location;
    public int Seats;
    public String date;
    public String starting_time;
    public String end_time;
    public EventDetail(int eid,String title,String club,String Location,int seats,String image_url,String description_url,String date,String starting_time,String end_time ){
       this.eid=eid;
        this.title=title;
        this.description_url=description_url;
        this.image_url=image_url;
        this.club=club;
        this.Location=Location;
        this.Seats=seats;
        this.date=date;
        this.starting_time=starting_time;
        this.end_time=end_time;
    }

    public String getImage_url() {
        return image_url;
    }
    public String getDescription_url(){
        return  description_url;
    }
    public String getTitle(){
        return  title;
    }
    public String getClub(){
        return club;
    }
    public String getLocation(){
        return Location;
    }
    public int getSeats(){
        return Seats;
    }
    public String getDate(){return date;}
    public String getStarting_time(){return starting_time;}
    public String getEnd_time(){return end_time;}
    public int getEid(){return eid;}
}
