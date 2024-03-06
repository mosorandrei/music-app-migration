package com.enigmacamp.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.enigmacamp.dao.impl.db.SingerDaoDBImpl;
import com.enigmacamp.entities.Singer;
import com.enigmacamp.enums.Gender;
import com.enigmacamp.repositories.SingerRepository;

import javassist.NotFoundException;

public class SingerServiceIntegrationMockTest {

    @InjectMocks
    private SingerDaoDBImpl service;

    @Mock
    private SingerRepository repo;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenFindAll_thenReturnListOfSinger() throws NotFoundException {

        List<Singer> singers = new ArrayList<Singer>();

        Singer singer = new Singer();
        singer.setFirstName("Banda");
        singer.setLastName("Neira");
        singer.setBirthDate(Date.valueOf("2000-01-01"));
        singer.setGender(Gender.MALE);

        Singer singer2 = new Singer();
        singer2.setFirstName("Edi");
        singer2.setLastName("Murwanto");
        singer2.setBirthDate(Date.valueOf("2000-01-01"));
        singer2.setGender(Gender.MALE);

        singers.add(singer);
        singers.add(singer2);

        when(repo.findAll()).thenReturn(singers);

        // test
        List<Singer> singerList = service.findAll();
        int expected = singers.size();

        Assertions.assertEquals(expected, singerList.size());
    }

    @Test
    public void whenFindById_thenReturnSinger() throws NotFoundException {

        // given
        Singer singer = new Singer();
        singer.setId("01");
        singer.setFirstName("Edi");
        singer.setLastName("Murwanto");
        singer.setBirthDate(Date.valueOf("2000-01-01"));
        singer.setGender(Gender.MALE);

        when(repo.findById(singer.getId())).thenReturn(Optional.of(singer));

        Singer actualSinger = service.findById("01");

        Assertions.assertEquals("01", actualSinger.getId());
        Assertions.assertEquals("Edi", actualSinger.getFirstName());
        Assertions.assertEquals("Murwanto", actualSinger.getLastName());
        Assertions.assertEquals(Date.valueOf("2000-01-01"), actualSinger.getBirthDate());
        Assertions.assertEquals(Gender.MALE, actualSinger.getGender());
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
        when(repo.save(singer)).thenReturn(singer);

        // then
        Singer actual = service.create(singer);
        Assertions.assertEquals(singer, actual);

    }

    @Test
    public void whenSaveSinger_thenReturnSavedAndCalledOnce() {

        // given
        Singer singer = new Singer();
        singer.setFirstName("Edi");
        singer.setLastName("Murwanto");
        singer.setBirthDate(Date.valueOf("2000-01-01"));
        singer.setGender(Gender.MALE);

        // when
        service.create(singer);

        // then
        verify(repo, times(1)).save(singer);

    }

    @Test
    public void whenUpdateSinger_thenReturnUpdatedSingerObject() throws NotFoundException {

        // given
        Singer singer = new Singer();
        singer.setId("01");
        singer.setFirstName("Edi");
        singer.setLastName("Murwanto");
        singer.setBirthDate(Date.valueOf("2000-01-01"));
        singer.setGender(Gender.MALE);

        // when
        when(repo.save(singer)).thenReturn(singer);

        singer.setFirstName("Uchiha");
        singer.setLastName("Minako");
        singer.setBirthDate(Date.valueOf("2009-02-12"));
        singer.setGender(Gender.FEMALE);
        when(repo.findById(singer.getId())).thenReturn(Optional.of(singer));
        when(service.update(singer)).thenReturn(singer);
        Singer actual = service.update(singer);

        // then
        Assertions.assertEquals("Uchiha", actual.getFirstName());
        Assertions.assertEquals("Minako", actual.getLastName());
        Assertions.assertEquals(Date.valueOf("2009-02-12"), actual.getBirthDate());
        Assertions.assertEquals(Gender.FEMALE, actual.getGender());
    }

    @Test
    public void deleteSingerTest() throws NotFoundException {

        // given
        Singer singer = new Singer();
        singer.setId("01");
        singer.setFirstName("Edi");
        singer.setLastName("Murwanto");
        singer.setBirthDate(Date.valueOf("2000-01-01"));
        singer.setGender(Gender.MALE);

        // when
        when(repo.findById(singer.getId())).thenReturn(Optional.of(singer));
        service.delete(singer.getId());

        // then
        verify(repo, times(1)).delete(singer);

    }
}
