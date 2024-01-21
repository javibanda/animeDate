package org.javibanda.model.entity.match;

import lombok.*;
import org.javibanda.model.entity.user.ShortProfile;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "match")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Match {
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", columnDefinition = "uuid", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "profile_id_1", nullable = false)
    private ShortProfile profile1;

    @ManyToOne
    @JoinColumn(name = "profile_id_2", nullable = false)
    private ShortProfile profile2;

    @Column(name = "match_profile_1")
    private Boolean matchProfile1;

    @Column(name = "match_profile_2")
    private Boolean matchProfile2;

    @Column(name = "matched_at", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Timestamp matchedAt;

    @Column(name = "is_block", columnDefinition = "boolean default false")
    private boolean isBlock;

    @Column(name = "blocked_by")
    private UUID blockedBy;

    @Column(name = "blocking_reason")
    private String blockingReason;

    @Column(name = "stop_follow", columnDefinition = "boolean default false")
    private boolean stopFollow;

}
