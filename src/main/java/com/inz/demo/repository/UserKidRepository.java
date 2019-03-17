package com.inz.demo.repository;

import com.inz.demo.domain.User;
import com.inz.demo.domain.UserKid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserKidRepository extends JpaRepository<UserKid, Long> {

    @Query("SELECT u FROM UserKid u WHERE u.kid = :id")
    List<UserKid> findByKidId(@Param("id") Long id);

}
