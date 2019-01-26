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

    private String username;
    private double last_latitude;
    private double last_longitude;

    private boolean isVisible;

    private LocalDateTime last_seen_date;

    @ElementCollection(targetClass = User.class)
    @MapKey(name="id")
    private Map<Long, User> friend;
}
