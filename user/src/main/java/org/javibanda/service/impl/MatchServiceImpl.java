package org.javibanda.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.javibanda.mapper.MatchMapper;
import org.javibanda.model.entity.match.Match;
import org.javibanda.model.entity.user.ShortProfile;
import org.javibanda.model.enums.Sex;
import org.javibanda.model.enums.SexualOrientation;
import org.javibanda.repository.MatchRepository;
import org.javibanda.service.MatchService;
import org.javibanda.service.ProfileService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final MatchRepository repository;

    private final ProfileService profileService;

    @Override
    public List<ShortProfile> getProfilesForMatches(UUID profileId) {
        val profile = profileService.getShortProfile(profileId);
        return repository.getProfilesForMatches(profileId, getSexParameter(profile), PageRequest.of(0, 20));
    }

    @Override
    public void createMatch(UUID yourProfileId, UUID matchedProfileId, Boolean matchAnswer) {
        var matchEntity = getMatch(yourProfileId, matchedProfileId);
        if (matchExist(matchEntity)) {
            repository.save(MatchMapper.updateEntity(matchEntity, matchAnswer));
        } else {
            val yourProfile = profileService.getShortProfile(yourProfileId);
            val matchedProfile = profileService.getShortProfile(matchedProfileId);
            repository.save(MatchMapper.toEntity(yourProfile, matchedProfile, matchAnswer));
        }
    }

    @Override
    public List<ShortProfile> getProfilesWhoLikedMe(UUID yourProfileId) {
        return repository.getProfilesWhoLikedMe(yourProfileId, PageRequest.of(0, 50));
    }

    @Override
    public Integer getCountProfilesWhoLikedMe(UUID yourProfileId) {
        return repository.getCountProfilesWhoLikedMe(yourProfileId);
    }

    @Override
    public List<ShortProfile> getMatches(UUID yourProfileId) {
        val list = repository.getMatchedProfiles(yourProfileId);
        return list.stream().map(match ->
                match.getProfile1().getId().equals(yourProfileId) ?
                        match.getProfile2() : match.getProfile1())
                .collect(Collectors.toList());
    }

    private Match getMatch(UUID yourProfileId, UUID matchedProfileId) {
        return repository.findByProfiles(matchedProfileId, yourProfileId);
    }

    private boolean matchExist(Match match) {
        return match != null;
    }

    private Sex getSexParameter(ShortProfile profile) {
        val profileSex = profile.getSex();
        val profileSexualOrientation = profile.getSexualOrientation();
        if (Sex.MALE == profileSex) {
            return getSexParameterMale(profileSexualOrientation);
        } else {
            return getSexParameterFemale(profileSexualOrientation);
        }
    }

    private Sex getSexParameterMale(SexualOrientation sexualOrientation) {
        switch (sexualOrientation) {
            case HETEROSEXUAL:
                return Sex.MALE;
            case HOMOSEXUAL:
                return Sex.FEMALE;
            case BISEXUAL:
                return Sex.BOTH;
        }
        return null;
    }

    private Sex getSexParameterFemale(SexualOrientation sexualOrientation) {
        switch (sexualOrientation) {
            case HETEROSEXUAL:
                return Sex.FEMALE;
            case HOMOSEXUAL:
                return Sex.MALE;
            case BISEXUAL:
                return Sex.BOTH;
        }
        return null;
    }
}
