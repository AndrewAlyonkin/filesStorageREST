package edu.alenkin.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 *
 * Represents the {@link StoredFile} download history for {@link User}
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

@Entity
@Table(name = "events")
public class Event extends BaseEntity {
    @OneToOne
    private StoredFile storedFile;

    @CreationTimestamp
    @Column(name = "download_time", columnDefinition = "TIMESTAMP")
    private Timestamp downloadDateTime;

    @ManyToOne
    private User user;

    public Event(StoredFile storedFile) {
        this.storedFile = storedFile;
    }
}
