package com.javathon.backend.model.db.events.interf;

import com.javathon.backend.model.db.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    private double latitude;
    private double longitude;

    private LocalDateTime createTime;

    private String description;

    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    private User author;
}
