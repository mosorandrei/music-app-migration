package com.enigmacamp.dto;

import com.enigmacamp.enums.Genre;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.validation.constraints.Size;
import java.sql.Date;

@Schema(description = "Album DTO for create new album")
public class AlbumFormDto {
	
	@Size(max = 100, message = "Name of album can't more than 100 character")
	@Schema(description = "Name of the album", requiredMode = Schema.RequiredMode.REQUIRED)
	private String name;
	
	@Size(min = 4, max = 4, message = "Year of release must min and max = 4")
	@Schema(description = "Year of release the album", requiredMode = Schema.RequiredMode.REQUIRED, example = "2020-01-01")
	private Date releaseDate;
	
	@Size(message = "Genre of albums")
	@Schema(description = "Genre of albums", requiredMode = Schema.RequiredMode.REQUIRED)
	private Genre genre;
	
	@Size(message = "Insert valid singer id to successfully create the album")
	@Schema(description = "Singer identifier of the album", requiredMode = Schema.RequiredMode.REQUIRED)
	private String singerId;
	
	public AlbumFormDto() {
		super();
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

	public String getSingerId() {
		return singerId;
	}
	public void setSingerId(String singerId) {
		this.singerId = singerId;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

}
