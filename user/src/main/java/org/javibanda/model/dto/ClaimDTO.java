package org.javibanda.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.javibanda.model.enums.Role;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ClaimDTO {
    private UUID id;
    private UUID profileId;
    private Role role;

}
