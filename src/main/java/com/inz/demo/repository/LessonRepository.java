package com.inz.demo.repository;

import com.inz.demo.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    Optional<Lesson> findByLessonId(Long id);

    Optional<Lesson> findByDate(Date date);

}
