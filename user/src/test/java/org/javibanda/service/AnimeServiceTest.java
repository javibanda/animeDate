package org.javibanda.service;

import lombok.val;
import org.javibanda.mapper.AnimeMapper;
import org.javibanda.model.entity.anime.Anime;
import org.javibanda.repository.AnimeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class AnimeServiceTest {

    @InjectMocks
    private AnimeService animeService;

    @Mock
    private AnimeRepository animeRepository;

    @Mock
    private AnimeProfileService animeProfileService;


    @Test
    public void should_save_anime() {
        animeService.save("naruto");

        verify(animeRepository).save(new Anime("naruto"));
    }

    @Test
    public void should_return_anime() {
        val anime = getAnime();

        when(animeRepository.findByName(anyString())).thenReturn(anime);

        val expectedAnime = animeService.get("naruto");

        assertEquals(anime, expectedAnime);
    }

    @Test
    public void getAll() {

    }

    @Test
    public void getFavoriteAnimes() {
    }

    @Test
    public void saveFavoriteAnime() {
    }

    private Anime getAnime(){
        return AnimeMapper.toEntity("naruto");
    }
}