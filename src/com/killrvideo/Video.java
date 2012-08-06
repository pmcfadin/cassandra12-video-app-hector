package com.killrvideo;


import java.util.UUID;

public class Video {

	UUID videoId;
	String videoName;
	String username;
	String description;
	String[] tags;

	public Video(){
		super();	
	}
	
	public Video(UUID videoId, String videoName, String username, String description, String[] tags) {
		super();
		this.videoId = videoId;
		this.videoName = videoName;
		this.username = username;
		this.description = description;
		this.tags = tags;
	}
	
	public UUID getVideoId() {
		return videoId;
	}
	public void setVideoId(UUID videoId) {
		this.videoId = videoId;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	public String getDelimitedTags(){
		StringBuilder sb = new StringBuilder();

		for(String s: this.tags) {
		   sb.append(s).append(',');
		}

		sb.deleteCharAt(sb.length()-1);

		return sb.toString();
	}
	
}
