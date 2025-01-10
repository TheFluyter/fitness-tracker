package com.thefluyter.fitnesstracker.serivce;

import com.thefluyter.fitnesstracker.model.ExerciseLog;
import com.thefluyter.fitnesstracker.repository.ExerciseLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExerciseLogService {

    private final ExerciseLogRepository exerciseLogRepository;

    public List<ExerciseLog> findAll() {
        return exerciseLogRepository.findAll();
    }

    public void save(ExerciseLog exerciseLog) {
        ExerciseLog saved = exerciseLogRepository.save(exerciseLog);
        log.info("Saved exercise {}", saved);
    }
}
