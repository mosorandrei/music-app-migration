package com.enigmacamp.controllers;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enigmacamp.dao.SongDao;
import com.enigmacamp.dto.CommonResponse;
import com.enigmacamp.dto.SongFormDto;
import com.enigmacamp.entities.Song;
import com.enigmacamp.utils.ObjectMapperUtils;
;
import javassist.NotFoundException;

@RestController
@RequestMapping("songs")
@Tag(name = "Songs")
public class SongController {

	@Autowired
	SongDao songDao;

	@GetMapping("")
	@Operation(summary = "Return list of song.",  responses = {@ApiResponse(responseCode = "200")})
	public ResponseEntity<CommonResponse<List<Song>>> findAll(@RequestParam(required = false) String title) {

		if (title != null) {
			List<Song> songs = songDao.findByTitle(title);
			return new ResponseEntity<>(new CommonResponse<List<Song>>(songs), HttpStatus.OK);
		} else {
			List<Song> songs = songDao.findAll();
			return new ResponseEntity<>(new CommonResponse<List<Song>>(songs), HttpStatus.OK);
		}
	}

	@GetMapping("/{id}")
	@Operation(summary = "Return a song by their identifier. 404 if does not exist.", responses = {@ApiResponse(responseCode = "200")})
	public ResponseEntity<CommonResponse<Song>> findById(@PathVariable String id) throws NotFoundException {

		Song song = songDao.findById(id);
		return new ResponseEntity<>(new CommonResponse<Song>(song), HttpStatus.OK);

	}

	@PostMapping("")
	@Operation(summary = "Create new songs.")
	public ResponseEntity<CommonResponse<Song>> create(@RequestBody SongFormDto form) throws NotFoundException {

		Song song = songDao.create(form);
		return new ResponseEntity<>(new CommonResponse<Song>("201", "OK", song),
				HttpStatus.CREATED);

	}

	@PutMapping("")
	@Operation(summary = "Update a song.")
	public ResponseEntity<CommonResponse<Song>> update(@RequestBody SongFormDto song) throws NotFoundException {

		Song updatedSong = songDao.update(ObjectMapperUtils.map(song, Song.class));
		return new ResponseEntity<>(new CommonResponse<Song>(updatedSong), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete song by their identifier.")
	public ResponseEntity<CommonResponse<Song>> delete(@PathVariable String id) throws NotFoundException {

		songDao.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
}
