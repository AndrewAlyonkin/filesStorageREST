package edu.alenkin.model;

import lombok.*;

import javax.persistence.*;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 *
 * Contains information about stored file and it URI in storage (server file system)
 */
@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "files")
public class StoredFile extends BaseEntity {
    @Column(name = "file_uri", columnDefinition = "VARCHAR")
    private String fileURI;

    @Column(name = "size", columnDefinition = "BIGINT")
    private long size;

    @OneToOne(mappedBy = "storedFile")
    private Event event;

    @ManyToOne
    private User user;

    public StoredFile(Long id, String fileURI, long size, Event event, User user) {
        super(id);
        this.fileURI = fileURI;
        this.size = size;
        this.event = event;
        this.user = user;
    }
    public StoredFile(StoredFile file) {
        this(file.getId(), file.getFileURI(), file.getSize(), file.getEvent(), file.getUser());
    }
    public StoredFile(String fileURI, long size, User user) {
        this(null, fileURI, size, null, user);
    }

    public StoredFile(Long id, String fileURI, long size, User user) {
        this(id, fileURI, size, null, user);
    }
}
