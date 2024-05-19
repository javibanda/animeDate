package org.javibanda.repository;

import org.javibanda.model.entity.match.Match;
import org.javibanda.model.entity.user.Profile;
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

    @Query( "SELECT DISTINCT p FROM Profile p " +
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
    List<Profile> getProfilesForMatches(UUID profileId, Sex sex, Pageable pageable);

    @Query("Select m from Match m where m.profile1.id = ?1 and m.profile2.id = ?2")
    Match findByProfiles(UUID profileId1, UUID profile2Id);

    @Query("select m.profile1 from Match m " +
            "where m.profile1.id = ?1 " +
            "and m.matchProfile2 is null " +
            "and m.matchProfile1 = true ")
    List<ShortProfile> getProfilesWhoLikedMe(UUID yourProfileId, Pageable pageable);

    @Query("select count(m.profile1) from Match m " +
            "where m.profile1.id = ?1 " +
            "and m.matchProfile2 is null " +
            "and m.matchProfile1 = true ")
    Integer getCountProfilesWhoLikedMe(UUID yourProfile);

    @Query("select m " +
            "from Match m " +
            "where (m.profile1.id = ?1 or m.profile2.id = ?1) " +
            "and m.matchProfile1 = true " +
            "and m.matchProfile2 = true")
    List<Match> getMatchedProfiles(UUID yourProfileId);

    //@Query("select case " +
    //        "when m.profile2.id = ?1 then m.profile1 " +
    //        "else m.profile2 " +
    //        "end " +
    //        "from Match m " +
    //        "where (m.profile1.id = ?1 or m.profile2.id = ?1) " +
    //        "and m.matchProfile1 = true " +
    //        "and m.matchProfile2 = true")
    //List<ShortProfile> getMatchedProfiles(UUID yourProfileId);

}

