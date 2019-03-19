package com.inz.demo.repository;

import com.inz.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserLogin(String login);

    User findByUserId(Long id);

    List<User> findAllByUserClassIsNotNull();
}