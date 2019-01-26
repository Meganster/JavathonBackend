package com.javathon.backend.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long imei;
    private long vkId;

    private String username;
    private double lastLatitude;
    private double lastLongitude;

    private boolean isVisible;

    private LocalDateTime lastSeenDate;

    private String token;
    private String recovery_code;

    @ElementCollection(targetClass = User.class)
    @CollectionTable(name = "friends", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "friend_id"}, name = "friends")})
    @MapKey(name="id")
    private Map<Long, User> friend;
}
