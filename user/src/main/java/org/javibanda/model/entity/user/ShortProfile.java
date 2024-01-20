package org.javibanda.model.entity.user;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Table(name = "profile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShortProfile {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;
}
