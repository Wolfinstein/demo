package com.inz.demo.repository;

import com.inz.demo.domain.UserKid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserKidRepository extends JpaRepository<UserKid, Long> {

    List<UserKid> findByKid_UserId(Long id);

    List<UserKid> findByUser_UserId(Long id);

}
