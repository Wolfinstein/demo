package com.inz.demo.repository;

import com.inz.demo.domain.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
    Optional<Class> findByClassId(Long id);

    Optional<Class> findByClassSign(String sign);
}
