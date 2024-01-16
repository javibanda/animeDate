package org.javibanda.repository;
import org.javibanda.model.entity.anime.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface AnimeRepository extends JpaRepository<Anime, String> {
    @Query("select anime from Anime anime join AnimeProfile animeProfile where animeProfile.profile = ?1")
    List<Anime> findAnimeByProfile(UUID profileId);

    Anime findByName(String animeName);
    @Query("select anime.name from Anime anime")
    List<String> findAllAnimeName();
}
