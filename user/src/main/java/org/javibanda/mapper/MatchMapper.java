package org.javibanda.mapper;

import org.javibanda.model.entity.match.Match;
import org.javibanda.model.entity.user.ShortProfile;

import java.sql.Timestamp;

public class MatchMapper {
    public static Match toEntity(ShortProfile yourProfile, ShortProfile matchedProfile, Boolean match){
        return  Match.builder()
                .profile1(yourProfile)
                .profile2(matchedProfile)
                .matchProfile1(match)
                .matchedAt(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public static Match updateEntity(Match matchEntity, Boolean matchAnswer){
        matchEntity.setMatchProfile2(matchAnswer);
        matchEntity.setMatchedAt(new Timestamp(System.currentTimeMillis()));
        return matchEntity;
    }
}
