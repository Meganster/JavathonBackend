package com.javathon.backend.dao;

import com.javathon.backend.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findById(long id);
    User findByImei(long imei);
    User findByVkId(long id);
    User findUserByToken(String token);
}
