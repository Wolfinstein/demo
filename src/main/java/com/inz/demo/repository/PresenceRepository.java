package com.inz.demo.repository;

import com.inz.demo.domain.Presence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresenceRepository extends JpaRepository<Presence, Long> {

    List<Presence> findByStudent_UserIdAndType(Long id, String type);

    List<Presence> findByStudent_UserId(Long id);

    List<Presence> findByLessonLessonId(Long id);

}
