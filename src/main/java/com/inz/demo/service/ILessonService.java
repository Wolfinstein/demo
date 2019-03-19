package com.inz.demo.service;

import com.inz.demo.domain.Lesson;
import com.inz.demo.util.DTOs.PresenceListDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface ILessonService {
    void createLesson(Long id);

    void checkPresence(Map<Long, String> presences, Long lessonId);

    void changePresence(Long presenceId, String presenceType);

    void deleteLesson(Long lessonId);

    List<Lesson> getLessons();

    Optional<Lesson> getLesson(Long id);

    void updateTopic(String topic, Long lessonId);

    List<PresenceListDTO> getPresencesList(Long id);
}
