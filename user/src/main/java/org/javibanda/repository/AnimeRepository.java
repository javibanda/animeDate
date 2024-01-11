package org.javibanda.repository;
import org.javibanda.model.entity.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AnimeRepository extends JpaRepository<Anime, String> {
}
