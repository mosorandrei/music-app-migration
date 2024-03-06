package com.enigmacamp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Null;

@Schema(description = "Class for Request body of create new Songs.")
public class SongFormDto {
	
	@Schema(description = "Title of songs.")
	private String title;
	
	@Schema(description = "Content of song")
	private String content;
	
	@Schema(description = "Singer id of song")
	private String singer;
	
	@Null
	@Schema(description = "Album id of song")
	private String album;
	
	public SongFormDto() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}
}
