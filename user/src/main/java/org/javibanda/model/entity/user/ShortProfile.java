package org.javibanda.model.entity.user;

import lombok.*;
import org.javibanda.model.enums.Sex;
import org.javibanda.model.enums.SexualOrientation;

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

    @Column(name = "sex", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    protected Sex sex;

    @Column(name = "sexual_orientation", nullable = false, length = 200)
    @Enumerated(EnumType.STRING)
    protected SexualOrientation sexualOrientation;

}
