package com.inz.demo.service.impl;

import com.inz.demo.domain.User;
import com.inz.demo.domain.UserKid;
import com.inz.demo.repository.ClassRepository;
import com.inz.demo.repository.UserKidRepository;
import com.inz.demo.repository.UserRepository;
import com.inz.demo.service.IUserService;
import com.inz.demo.util.DTOs.UserDTO;
import com.inz.demo.util.DTOs.UserDTOv2;
import com.inz.demo.util.DTOs.UserTeacherUpdateDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static com.inz.demo.util.methods.SplitIntegers.splitStringToIntArrays;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final ClassRepository classRepository;
    private final UserKidRepository userKidRepository;

    public UserServiceImpl(UserRepository userRepository, ClassRepository classRepository, UserKidRepository userKidRepository) {
        this.userRepository = userRepository;
        this.classRepository = classRepository;
        this.userKidRepository = userKidRepository;
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        return userRepository.findByUserLogin(login);
    }

    @Override
    public void createUser(UserDTO userDTO) {
        User user = User.builder()
                .birthDate(userDTO.getBirthDate())
                .isUserParent(userDTO.getIsUserParent())
                .isUserStudent(userDTO.getIsUserStudent())
                .isUserTeacher(userDTO.getIsUserTeacher())
                .userLogin(userDTO.getLogin())
                .userName(userDTO.getName())
                .userSurname(userDTO.getSurname())
                .userEmail(userDTO.getEmail())
                .userTimestamp(Calendar.getInstance().getTime())
                .userModificationDate(Calendar.getInstance().getTime())
                .phoneNumber(userDTO.getPhone())
                .build();

        if (user.getIsUserStudent()) {
            user.setUserClass(classRepository.getOne(userDTO.getClassId()));
        }

        if (user.getIsUserParent()) {
            userRepository.save(user);
            User addKids = userRepository.findByUserLogin(userDTO.getLogin()).get();

            int[] arr = splitStringToIntArrays(userDTO.getKidsIds());
            List<UserKid> kids = new ArrayList<>();
            for (int id : arr) {
                kids.add(UserKid.builder()
                        .kid(userRepository.findByUserId((long) id))
                        .user(addKids)
                        .build());
            }
            addKids.setUserKids(kids);
            user.setUserPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
            userRepository.save(addKids);
        }

        user.setUserPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return Optional.ofNullable(userRepository.findByUserId(id));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateTeacher(Long id, UserTeacherUpdateDTO user) {
        User teacher = userRepository.findByUserId(id);
        teacher.setPhoneNumber(user.getPhone());
        teacher.setUserEmail(user.getEmail());
        teacher.setUserName(user.getName());
        teacher.setUserSurname(user.getSurname());
        teacher.setUserModificationDate(Calendar.getInstance().getTime());

        userRepository.save(teacher);
    }

    @Override
    public void updateUser(Long id, User user) {
        User toEdit = userRepository.findByUserId(id);
        toEdit.setUserName(user.getUserName());
        toEdit.setUserSurname(user.getUserSurname());
        toEdit.setPhoneNumber(user.getPhoneNumber());
        toEdit.setUserEmail(user.getUserEmail());
        userRepository.save(toEdit);
    }

    @Override
    public UserDTOv2 convertUserToDTO(Long id) {
        User user = userRepository.findByUserId(id);

        UserDTOv2 dto = UserDTOv2.builder()
                .birthDate(user.getBirthDate())
                .isUserParent(user.getIsUserParent())
                .modificationDate(user.getUserModificationDate())
                .name(user.getUserName())
                .surname(user.getUserSurname())
                .phone(user.getPhoneNumber())
                .role(user.getRoles())
                .isUserStudent(user.getIsUserStudent())
                .isUserTeacher(user.getIsUserTeacher())
                .email(user.getUserEmail())
                .build();

        List<User> users = new ArrayList<>();
        if (user.getIsUserParent()) {
            List<UserKid> kids = userKidRepository.findByUser_UserId(user.getUserId());
            for (UserKid u : kids) {
                users.add(u.getKid());
            }
            dto.setRelatives(users);
        } else if(user.getIsUserStudent()) {
            List<UserKid> parents = userKidRepository.findByKid_UserId(user.getUserId());
            for (UserKid u : parents) {
                users.add(u.getUser());
            }
            dto.setRelatives(users);
            dto.setClassSign(user.getUserClass().getClassSign());
        }

        return dto;
    }

}
