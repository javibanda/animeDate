package org.javibanda.service;

import org.javibanda.model.dto.ProfileRequest;
import org.javibanda.model.dto.ProfileResponseShort;
import org.javibanda.model.entity.user.ShortProfile;

import java.text.ParseException;
import java.util.UUID;

public interface ProfileService {

    void save(ProfileRequest request, UUID userId) throws ParseException;

    ProfileResponseShort getProfile(UUID profileId);

    ShortProfile getShortProfile(UUID profileId);
}
