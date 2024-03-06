package com.enigmacamp.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "songs")
@Schema(description = "Class representing songs")
public class Song {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "uuid")
	@Schema(description = "Unique identifier of song. No two songs can have the same id.", requiredMode = Schema.RequiredMode.REQUIRED)
	private String id;

	@Column(name = "title", length = 100, nullable = false)
	@Schema(description = "Title of song.", requiredMode = Schema.RequiredMode.REQUIRED)
	private String title;

	@Column(name = "content", length = 1000, nullable = false)
	@Schema(description = "Contents of song.", requiredMode = Schema.RequiredMode.REQUIRED)
	private String content;
	
	@Column
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@ManyToOne
	@JoinColumn(name = "singer", nullable = false)
	@Schema(description = "Singer of the song", requiredMode = Schema.RequiredMode.REQUIRED)
	private Singer singer;

	@ManyToOne
	@JoinColumn(name = "album", nullable = true)
	@Schema(description = "Album of the song", requiredMode = Schema.RequiredMode.REQUIRED)
	private Album album;
	
	public Song() {
		//just default constructor
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Singer getSinger() {
		return singer;
	}

	public void setSinger(Singer singer) {
		this.singer = singer;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
}
