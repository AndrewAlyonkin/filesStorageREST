package edu.alenkin.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 *
 * Represents the owner of {@link StoredFile} and {@link Event}
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "userName", columnDefinition = "VARCHAR")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<StoredFile> storedFiles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Event> events;

}
