package com.dzakdzaks.qrcode;

import com.google.gson.annotations.SerializedName;

public class ResponseMovie{

	@SerializedName("duration")
	private String duration;

	@SerializedName("price")
	private String price;

	@SerializedName("director")
	private String director;

	@SerializedName("name")
	private String name;

	@SerializedName("rating")
	private double rating;

	@SerializedName("genre")
	private String genre;

	@SerializedName("poster")
	private String poster;

	@SerializedName("released")
	private boolean released;

	public void setDuration(String duration){
		this.duration = duration;
	}

	public String getDuration(){
		return duration;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setDirector(String director){
		this.director = director;
	}

	public String getDirector(){
		return director;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setRating(double rating){
		this.rating = rating;
	}

	public double getRating(){
		return rating;
	}

	public void setGenre(String genre){
		this.genre = genre;
	}

	public String getGenre(){
		return genre;
	}

	public void setPoster(String poster){
		this.poster = poster;
	}

	public String getPoster(){
		return poster;
	}

	public void setReleased(boolean released){
		this.released = released;
	}

	public boolean isReleased(){
		return released;
	}
}