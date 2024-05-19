package org.javibanda.service;

import org.javibanda.model.entity.user.Profile;
import org.javibanda.model.entity.user.ShortProfile;

import java.util.List;
import java.util.UUID;

public interface MatchService{

    List<Profile> getProfilesForMatches(UUID profileId);

    void createMatch(UUID yourProfileId, UUID matchedProfileId, Boolean matchAnswer);

    List<ShortProfile> getProfilesWhoLikedMe(UUID yourProfileId);

    Integer getCountProfilesWhoLikedMe(UUID yourProfileId);

    List<ShortProfile> getMatches(UUID yourProfileId);
}
