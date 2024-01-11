package org.javibanda.repository;
import org.javibanda.model.entity.AnimeCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AnimeCharacterRepository extends JpaRepository<AnimeCharacter, String> {
}
