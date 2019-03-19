package com.inz.demo.service;

import com.inz.demo.domain.User;
import com.inz.demo.util.DTOs.KidDTO;
import com.inz.demo.util.DTOs.UserDTO;
import com.inz.demo.util.DTOs.UserDTOv2;
import com.inz.demo.util.DTOs.UserTeacherUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IUserService {
    Optional<User> findUserByLogin(String login);

    void createUser(UserDTO userDTO);

    List<UserDTO> getUsers();

    Optional<User> findUserById(Long id);

    void deleteUser(Long id);

    void updateTeacher(Long id, UserTeacherUpdateDTO user);

    void updateUser(Long id, User user);

    UserDTOv2 convertUserToDTO(Long id);

    List<KidDTO> getPotentialKids();

    void editKids(Long id, String ids);

    void editUser(Long id, UserDTO user);

}
