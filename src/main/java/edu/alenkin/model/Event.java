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
@NoArgsConstructor

@Entity
@Table(name = "events")
public class Event extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "file_id")
    private StoredFile storedFile;

    @CreationTimestamp
    @Column(name = "download_time", columnDefinition = "TIMESTAMP")
    private Timestamp downloadDateTime;

    @ManyToOne
    private User user;

    public Event(StoredFile storedFile) {
        this.storedFile = storedFile;
    }

    public Event(Long eventId, Timestamp downloadDateTime, StoredFile file, User user) {
        super(eventId);
        this.downloadDateTime = downloadDateTime;
        this.storedFile = file;
        this.user = user;
    }
}
