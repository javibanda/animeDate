package org.javibanda.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.javibanda.model.enums.Role;

@Getter
@Setter
@AllArgsConstructor
public class ClaimDTO {
    private String id;
    private Role role;
}
