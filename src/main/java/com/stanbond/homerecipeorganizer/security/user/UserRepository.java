package com.stanbond.homerecipeorganizer.security.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String  login);
    Optional<User> findByEmail(String email);
    Optional<User> findByLoginOrEmail(String Login, String email);
    User findById(long userId);
    List<User> findAll();
    boolean existsUsersByLogin(String login);
    boolean existsUsersByEmail(String email);

}
