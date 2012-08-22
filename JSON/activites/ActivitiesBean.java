package com.rahul.cheerfoolz.activites;

import java.io.Serializable;

public class ActivitiesBean implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String title;
	public String image;
	public String tid;
	public String Goal;
	public String WhoCanPlay;
	public String Player;
	public String Duration;
	public String Things;
	public String Rules;
	public String HowToPlay;
	//public String Details;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getGoal() {
		return Goal;
	}
	public void setGoal(String goal) {
		Goal = goal;
	}
	public String getWhoCanPlay() {
		return WhoCanPlay;
	}
	public void setWhoCanPlay(String whoCanPlay) {
		WhoCanPlay = whoCanPlay;
	}
	public String getPlayer() {
		return Player;
	}
	public void setPlayer(String player) {
		Player = player;
	}
	public String getDuration() {
		return Duration;
	}
	public void setDuration(String duration) {
		Duration = duration;
	}
	public String getThings() {
		return Things;
	}
	public void setThings(String things) {
		Things = things;
	}
	public String getRules() {
		return Rules;
	}
	public void setRules(String rules) {
		Rules = rules;
	}
	public String getHowToPlay() {
		return HowToPlay;
	}
	public void setHowToPlay(String howToPlay) {
		HowToPlay = howToPlay;
	}
	/*public String getDetails() {
		return Details;
	}
	public void setDetails(String details) {
		Details = details;
	}*/
	
	
	
}
