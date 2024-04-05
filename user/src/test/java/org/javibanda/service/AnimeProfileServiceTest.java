package org.javibanda.service;

import org.javibanda.mapper.AnimeProfileMapper;
import org.javibanda.model.entity.anime.AnimeProfile;
import org.javibanda.repository.AnimeProfileRepository;
import org.javibanda.service.impl.AnimeProfileServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;



@RunWith(MockitoJUnitRunner.class)
public class AnimeProfileServiceTest {
    @InjectMocks
    private AnimeProfileServiceImpl animeProfileService;
    @Mock
    private AnimeProfileRepository repository;


    @Test
    public void toEntity() {
        UUID profileId = UUID.randomUUID();
        List<String> animes = List.of("Anime1", "Anime2");
        List<AnimeProfile> result = AnimeProfileMapper.toEntity(profileId, animes);

        animeProfileService.save(profileId, animes);

        for (AnimeProfile animeProfile : result) {
            assertTrue(animes.contains(animeProfile.getAnime()));
            assertNotNull(animeProfile.getId());
        }
    }
}