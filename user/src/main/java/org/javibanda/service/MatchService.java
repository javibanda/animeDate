package org.javibanda.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.javibanda.model.entity.user.ShortProfile;
import org.javibanda.model.enums.Sex;
import org.javibanda.model.enums.SexualOrientation;
import org.javibanda.repository.MatchRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository repository;
    private final ProfileService profileService;

    public List<ShortProfile> getMatchedProfiles(UUID profileId){
        val profile = profileService.getShortProfile(profileId);

        return repository.getMatches(profileId,
                getSexParameter(profile),
                PageRequest.of(0,20));
    }

    private Sex getSexParameter(ShortProfile profile){
        val profileSex = profile.getSex();
        val profileSexualOrientation = profile.getSexualOrientation();
        if (Sex.MALE == profileSex){
            return getSexParameterMale(profileSexualOrientation);
        }else{
            return getSexParameterFemale(profileSexualOrientation);
        }
    }

    private Sex getSexParameterMale(SexualOrientation sexualOrientation){
        switch (sexualOrientation){
            case HETEROSEXUAL:
                return Sex.MALE;
            case HOMOSEXUAL:
                return Sex.FEMALE;
            case BISEXUAL:
                return Sex.BOTH;
        }
        return null;
    }

    private Sex getSexParameterFemale(SexualOrientation sexualOrientation){
        switch (sexualOrientation){
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
