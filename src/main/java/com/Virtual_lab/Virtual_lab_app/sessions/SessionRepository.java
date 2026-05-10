package com.Virtual_lab.Virtual_lab_app.sessions;

import com.Virtual_lab.Virtual_lab_app.sessions.SessionModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SessionRepository extends MongoRepository<SessionModel, String> {

    Optional<SessionModel> findByMentorCode(String mentorCode);

    Optional<SessionModel> findByStudentCode(String studentCode);
}