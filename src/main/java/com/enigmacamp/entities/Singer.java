package com.enigmacamp.entities;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.enigmacamp.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "singers")
@Schema(description = "Class representing singer.")
public class Singer {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "uuid")
	@Schema(description = "Unique identifier of singer. No two singers can have the same id.", requiredMode = Schema.RequiredMode.REQUIRED)
	private String id;
	
	@Column(name = "first_name", length = 15, nullable = false)
	@Schema(description = "First name of singer", requiredMode = Schema.RequiredMode.REQUIRED)
	private String firstName;
	
	@Column(name = "last_name", length = 30, nullable = false)
	@Schema(description = "Last name of singer", requiredMode = Schema.RequiredMode.REQUIRED)
	private String lastName;
	
	@Column(name = "birth_date", nullable = false)
	@Schema(description = "Birth Date of singer", type = "Date", requiredMode = Schema.RequiredMode.REQUIRED)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "gender", length = 8, nullable = false)
	@Schema(description = "Gender of singer", type = "Enum", requiredMode = Schema.RequiredMode.REQUIRED)
	private Gender gender;
	
	
	@Column
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, mappedBy="singer")
	@Schema(description = "List of album that haved by the singer")
	private List<Album> albums;
	
	public Singer() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
