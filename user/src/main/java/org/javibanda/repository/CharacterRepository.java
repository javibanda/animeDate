package org.javibanda.repository;
import org.javibanda.model.entity.anime.AnimeCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CharacterRepository extends JpaRepository<AnimeCharacter, String> {
}
