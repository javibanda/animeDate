package org.javibanda.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MatchRequest {
    private UUID profileId;
    private Boolean match;
}
