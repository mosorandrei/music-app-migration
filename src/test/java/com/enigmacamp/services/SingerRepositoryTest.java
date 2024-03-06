package com.enigmacamp.services;

import com.enigmacamp.App;
import com.enigmacamp.config.DaoSpringConfig;
import com.enigmacamp.entities.Singer;
import com.enigmacamp.enums.Gender;
import com.enigmacamp.repositories.SingerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = { App.class, DaoSpringConfig.class })
public class SingerRepositoryTest {

	@Autowired
	private SingerRepository singerRepo;

	@Test
	public void whenFindById_thenReturnSinger() {

		// given
		Singer singer = new Singer();
		singer.setFirstName("Edi");
		singer.setLastName("Murwanto");
		singer.setBirthDate(Date.valueOf("2000-01-01"));
		singer.setGender(Gender.MALE);
		singerRepo.save(singer);

		// when
		Singer found = singerRepo.findById(singer.getId()).orElse(null);

		// then
		Assertions.assertEquals(found, singer);
	}

	@Test
	public void whenSaveSinger_thenReturnSavedSingerObject() {

		// given
		Singer singer = new Singer();
		singer.setFirstName("Edi");
		singer.setLastName("Murwanto");
		singer.setBirthDate(Date.valueOf("2000-01-01"));
		singer.setGender(Gender.MALE);

		// when
		Singer actual = singerRepo.save(singer);

		// then
		Assertions.assertEquals(singer, actual);
		Assertions.assertNotNull(singer.getId());
	}

	@Test
	public void whenSaveSinger_thenSingerListLengthAddedOne() {

		// given
		Singer singer = new Singer();
		singer.setFirstName("Edi");
		singer.setLastName("Murwanto");
		singer.setBirthDate(Date.valueOf("2000-01-01"));
		singer.setGender(Gender.MALE);

		int lengthBefore = singerRepo.findAll().size();

		// when
		singerRepo.save(singer);

		// then
		Assertions.assertEquals(singerRepo.findAll().size(), lengthBefore + 1);
	}

	@Test
	public void whenUpdateSinger_thenReturnUpdatedSingerObject() {

		// given
		Singer singer = new Singer();
		singer.setFirstName("Edi");
		singer.setLastName("Murwanto");
		singer.setBirthDate(Date.valueOf("2000-01-01"));
		singer.setGender(Gender.MALE);
		singerRepo.save(singer);

		// when
		singer.setFirstName("Uchiha");
		singer.setLastName("Sisui");
		Singer actual = singerRepo.save(singer);

		Singer expected = singerRepo.findById(singer.getId()).orElse(null);

		// then
		Assertions.assertEquals(actual, expected);
	}

	@Test
	public void whenFindAll_thenReturnListOfSinger() {

		// given
		Singer singer = new Singer();
		singer.setFirstName("Edi");
		singer.setLastName("Murwanto");
		singer.setBirthDate(Date.valueOf("2000-01-01"));
		singer.setGender(Gender.MALE);

		singerRepo.save(singer);

		Singer singer2 = new Singer();
		singer2.setFirstName("Uchiha");
		singer2.setLastName("Sisui");
		singer2.setBirthDate(Date.valueOf("2000-01-01"));
		singer2.setGender(Gender.MALE);

		singerRepo.save(singer2);

		// when
		List<Singer> singersActuals = singerRepo.findAll();

		// then
		Assertions.assertEquals(singerRepo.findAll(), singersActuals);
	}

	@Test
	public void whenDeleteSinger_thenSingerListLengthMinusOne() {

		// given
		Singer singer = new Singer();
		singer.setFirstName("Edi");
		singer.setLastName("Murwanto");
		singer.setBirthDate(Date.valueOf("2000-01-01"));
		singer.setGender(Gender.MALE);

		singerRepo.save(singer);

		Singer singer2 = new Singer();
		singer2.setFirstName("Uchiha");
		singer2.setLastName("Sisui");
		singer2.setBirthDate(Date.valueOf("2000-01-01"));
		singer2.setGender(Gender.MALE);

		singerRepo.save(singer2);

		int lengthBefore = singerRepo.findAll().size();

		// when
		singerRepo.delete(singer);

		// then
		Assertions.assertEquals(singerRepo.findAll().size(), lengthBefore - 1);

	}

	@Test
	public void whenDeleteSinger_thenSingerObjectDeleted() {

		// given
		Singer singer = new Singer();
		singer.setFirstName("Edi");
		singer.setLastName("Murwanto");
		singer.setBirthDate(Date.valueOf("2000-01-01"));
		singer.setGender(Gender.MALE);

		singerRepo.save(singer);

		Singer singer2 = new Singer();
		singer2.setFirstName("Uchiha");
		singer2.setLastName("Sisui");
		singer2.setBirthDate(Date.valueOf("2000-01-01"));
		singer2.setGender(Gender.MALE);

		singerRepo.save(singer2);

		// when
		singerRepo.delete(singer);

		// then
		Singer actual = singerRepo.findById(singer.getId()).orElse(null);
		Assertions.assertNull(actual);

	}

}
