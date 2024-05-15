package com.cubes4.api.dao;

import com.cubes4.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    Optional<User> findByLogin(String login);

    List<User> findByDeletedFalse();

    Optional<User> findByLoginAndDeleted(String login, boolean deleted);
}
