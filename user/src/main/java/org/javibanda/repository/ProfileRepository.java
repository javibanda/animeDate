package org.javibanda.repository;
import org.javibanda.model.entity.user.Profile;
import org.javibanda.model.entity.user.ShortProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {
    @Query("select profile.id from Profile profile where profile.userId = ?1")
    UUID findProfileId(UUID userId);

    @Query("select p from ShortProfile p where p.id = ?1")
    ShortProfile findShortProfileById(UUID id);

    Profile findProfileById(@NonNull UUID id);
}
