package com.javathon.backend.model;

import lombok.Data;

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
    private long vk_id;

    private String username;
    private double last_latitude;
    private double last_longitude;

    private boolean isVisible;

    private LocalDateTime last_seen_date;

    @ElementCollection(targetClass = User.class)
    @CollectionTable(name = "friends", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "friend_id"}, name = "friends")})
    @MapKey(name="id")
    private Map<Long, User> friend;
}
