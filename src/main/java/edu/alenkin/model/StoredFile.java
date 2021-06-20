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
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name = "files")
public class StoredFile extends BaseEntity {
    @Column(name = "file_uri", columnDefinition = "VARCHAR")
    private String fileURI;

    @Column(name = "size", columnDefinition = "BIGINT")
    private long size;

    @OneToOne(mappedBy = "file")
    private Event event;

    @ManyToOne
    private User user;
}
