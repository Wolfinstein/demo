package com.inz.demo.service.impl;

import com.inz.demo.domain.Lesson;
import com.inz.demo.domain.Presence;
import com.inz.demo.domain.Subject;
import com.inz.demo.domain.User;
import com.inz.demo.repository.*;
import com.inz.demo.service.ILessonService;
import com.inz.demo.service.INotificationService;
import com.inz.demo.util.DTOs.PresenceListDTO;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LessonServiceImpl implements ILessonService {

    private final LessonRepository lessonRepository;
    private final ClassRepository classRepository;
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;
    private final PresenceRepository presenceRepository;
    private final INotificationService notificationService;

    public LessonServiceImpl(LessonRepository lessonRepository, ClassRepository classRepository, SubjectRepository subjectRepository, UserRepository userRepository, PresenceRepository presenceRepository, INotificationService notificationService) {
        this.lessonRepository = lessonRepository;
        this.classRepository = classRepository;
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
        this.presenceRepository = presenceRepository;
        this.notificationService = notificationService;
    }

    @Override
    public void createLesson(Long id) {
        Subject subject = subjectRepository.getOne(id);

        Lesson lesson = Lesson.builder()
                .date(Calendar.getInstance().getTime())
                .lessonClass(subject.getSubjectClass())
                .subject(subject)
                .topic("Change me")
                .build();

        lessonRepository.save(lesson);

        List<User> students = subject.getSubjectClass().getUsers();
        List<Presence> presences = new ArrayList<>();
        for (User s : students) {
            presences.add(Presence.builder()
                    .date(Calendar.getInstance().getTime())
                    .lesson(lesson)
                    .student(s)
                    .teacher(subject.getTeacher())
                    .type("PRESENT")
                    .build());
        }
        presenceRepository.saveAll(presences);
    }

    @Override
    public void checkPresence(Map<Long, String> presences, Long lessonId) {
        List<Presence> presenceList = new ArrayList<>();

        for (Map.Entry<Long, String> entry : presences.entrySet()) {
            Presence presence = Presence.builder()
                    .date(Calendar.getInstance().getTime())
                    .lesson(lessonRepository.getOne(lessonId))
                    .student(userRepository.findByUserId(entry.getKey()))
                    .teacher(lessonRepository.getOne(lessonId).getSubject().getTeacher())
                    .type(entry.getValue())
                    .build();
            presenceList.add(presence);

            if (presence.getType().equals("ABSENT")) {
                notificationService.absenceNotification(presence.getStudent(), lessonId);
            }
        }
        presenceRepository.saveAll(presenceList);
    }

    @Override
    public void changePresence(Long presenceId, String presenceType) {
        Presence presence = presenceRepository.getOne(presenceId);
        presence.setType(presenceType);
        presence.setDate(Calendar.getInstance().getTime());
        presenceRepository.save(presence);
    }

    @Override
    public void deleteLesson(Long lessonId) {
        lessonRepository.deleteById(lessonId);
    }

    @Override
    public List<Lesson> getLessons() {
        return lessonRepository.findAll();
    }

    @Override
    public Optional<Lesson> getLesson(Long id) {
        return lessonRepository.findByLessonId(id);
    }

    @Override
    public void updateTopic(String topic, Long lessonId) {
        Lesson lesson = lessonRepository.getOne(lessonId);
        lesson.setTopic(topic);
        lessonRepository.save(lesson);
    }

    @Override
    public List<PresenceListDTO> getPresencesList(Long id) {
        List<Presence> presences = presenceRepository.findByLessonLessonId(id).stream().sorted((Comparator.comparing(o -> o.getStudent().getUserSurname()))).collect(Collectors.toList());
        List<PresenceListDTO> dtos = new ArrayList<>();

        for (Presence p : presences) {
            dtos.add(PresenceListDTO.builder()
                    .presenceType(p.getType())
                    .name(p.getStudent().getUserName())
                    .surname(p.getStudent().getUserSurname())
                    .date(p.getDate())
                    .id(p.getPresenceId())
                    .build());
        }
        return dtos;
    }

}
