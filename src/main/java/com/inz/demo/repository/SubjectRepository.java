package com.inz.demo.repository;

import com.inz.demo.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
