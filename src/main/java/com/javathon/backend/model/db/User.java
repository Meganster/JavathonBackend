package com.javathon.backend.model.db;

import com.javathon.backend.model.db.events.impl.EventMessage;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long vkId;

    private String imei;
    private String username;
    private double lastLatitude;
    private double lastLongitude;

    private boolean isVisible = true;
    private boolean isVisibleMessage = true;

    private LocalDateTime lastSeenDate;

    private String token;
    private String recovery_code;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "author")
    private List<EventMessage> messages;

    @ElementCollection(targetClass = User.class)
    @CollectionTable(name = "friends", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "friend_id"}, name = "friends")})
    @MapKey(name="id")
    private Map<Long, User> friend;
}
