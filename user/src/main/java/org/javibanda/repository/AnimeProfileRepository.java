package org.javibanda.repository;

import org.javibanda.model.entity.anime.AnimeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface AnimeProfileRepository extends JpaRepository<AnimeProfile, UUID> {
    Optional<AnimeProfile> getAnimeProfileByAnimeAndProfile(String anime, UUID profile);
}
