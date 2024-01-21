package org.javibanda.repository;

import org.javibanda.model.entity.match.Match;
import org.javibanda.model.entity.user.ShortProfile;
import org.javibanda.model.enums.Sex;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MatchRepository extends JpaRepository<Match, UUID> {

    @Query( "SELECT DISTINCT p FROM ShortProfile p " +
            "WHERE p NOT IN (" +
            "  SELECT m.profile1 FROM Match m " +
            "  WHERE m.profile2.id = ?1 " +
            "    AND m.matchProfile1 = true " +
            "    AND m.matchProfile2 = true " +
            ") " +
            "AND p NOT IN (" +
            "  SELECT m.profile2 FROM Match m " +
            "  WHERE m.profile1.id = ?1 " +
            ") " +
            "AND p.id != ?1 " +
            "AND p.sex != ?2 ")
    List<ShortProfile> getMatches(UUID profileId, Sex sex, Pageable pageable);
}
