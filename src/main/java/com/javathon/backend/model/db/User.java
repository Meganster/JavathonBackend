package com.javathon.backend.model.db;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public long getVkId() {
        return vkId;
    }

    public void setVkId(long vkId) {
        this.vkId = vkId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getLastLatitude() {
        return lastLatitude;
    }

    public void setLastLatitude(double lastLatitude) {
        this.lastLatitude = lastLatitude;
    }

    public double getLastLongitude() {
        return lastLongitude;
    }

    public void setLastLongitude(double lastLongitude) {
        this.lastLongitude = lastLongitude;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isVisibleMessage() {
        return isVisibleMessage;
    }

    public void setVisibleMessage(boolean visibleMessage) {
        isVisibleMessage = visibleMessage;
    }

    public LocalDateTime getLastSeenDate() {
        return lastSeenDate;
    }

    public void setLastSeenDate(LocalDateTime lastSeenDate) {
        this.lastSeenDate = lastSeenDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRecovery_code() {
        return recovery_code;
    }

    public void setRecovery_code(String recovery_code) {
        this.recovery_code = recovery_code;
    }

    public Map<Long, User> getFriend() {
        return friend;
    }

    public void setFriend(Map<Long, User> friend) {
        this.friend = friend;
    }
}
