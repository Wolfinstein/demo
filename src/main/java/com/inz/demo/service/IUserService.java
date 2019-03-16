package com.inz.demo.service;

import com.inz.demo.domain.User;
import com.inz.demo.util.DTOs.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IUserService {
    Optional<User> findUserByLogin(String login);

    void createUser(UserDTO userDTO);

    List<User> getUsers();

    Optional<User> findUserById(Long id);

    void deleteUser(Long id);

    void updateUser(Long id, User user);
}
