package com.enigmacamp.entities;

import com.enigmacamp.enums.Genre;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "albums")
@Schema(description = "Class representing albums")
public class Album {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "uuid")
	@Schema(description = "Unique identifier of album. No two albums can have the same id.", requiredMode = Schema.RequiredMode.REQUIRED)
	private String id;

	@Column(length = 100, nullable = false)
	@Schema(description = "Name of the album", requiredMode = Schema.RequiredMode.REQUIRED)
	private String name;
	
	@Column(nullable = false)
	@Schema(description = "Date of release the album", requiredMode = Schema.RequiredMode.REQUIRED)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date releaseDate;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 50)
	@Schema(description = "Genre of albums.")
	private Genre genre;
	
	@Column
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@Column(length = 150)
	@Schema(description = "Image path of the album", requiredMode = Schema.RequiredMode.REQUIRED)
	private String images;
	
	@ManyToOne
    @JoinColumn(name="singer", nullable=true)
	@Schema(description = "Singer of the album", requiredMode = Schema.RequiredMode.REQUIRED)
	private Singer singer;

	@OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, mappedBy="album")
	@Schema(description = "List of songs in the album")
	private List<Song> songs;
	
	public Album() {
		//just default constructor	
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Singer getSinger() {
		return singer;
	}

	public void setSinger(Singer singer) {
		this.singer = singer;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}	
}
