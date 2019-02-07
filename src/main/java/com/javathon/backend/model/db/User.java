package com.javathon.backend.model.db;

import com.javathon.backend.model.db.events.impl.EventMessage;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@Table(name = "users")
@DynamicUpdate
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long vkId;

    private String imei;
    private String firstName;
    private String lastName;
    private double lastLatitude;
    private double lastLongitude;

    private boolean isVisible = true;
    private boolean isVisibleMessage = true;

    private LocalDateTime lastSeenDate;

    private String token;
    private String recovery_code;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "author")
    private List<EventMessage> messages = new ArrayList<>();

    @ElementCollection(targetClass = User.class)
    @CollectionTable(name = "friends", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "friend_id"}, name = "friends")})
    @MapKey(name = "id")
    private Map<Long, User> friend = new HashMap<>();
}
