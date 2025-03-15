package com.thefluyter.fitnesstracker.service.exerciselog;

import com.thefluyter.fitnesstracker.model.exerciselog.ExerciseLog;
import com.thefluyter.fitnesstracker.model.exerciselog.ExerciseLogData;
import com.thefluyter.fitnesstracker.service.exercise.ExerciseMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExerciseLogMapperImpl implements ExerciseLogMapper {

    @Override
    public ExerciseLog exerciseLogDataToExerciseLog(ExerciseLogData exerciseLogData) {
        if ( exerciseLogData == null ) {
            return null;
        }

        ExerciseLog exerciseLog = new ExerciseLog();

        exerciseLog.setId(exerciseLogData.getId());
        exerciseLog.setReps1(exerciseLogData.getReps1());
        exerciseLog.setReps2(exerciseLogData.getReps2());
        exerciseLog.setReps3(exerciseLogData.getReps3());
        exerciseLog.setWeight1(exerciseLogData.getWeight1());
        exerciseLog.setWeight2(exerciseLogData.getWeight2());
        exerciseLog.setWeight3(exerciseLogData.getWeight3());
        exerciseLog.setDate(exerciseLogData.getDate());

        return exerciseLog;
    }

    @Override
    public List<ExerciseLogData> exerciseLogsToExerciseLogDatas(List<ExerciseLog> exerciseLogs) {
        if (exerciseLogs == null) {
            return Collections.emptyList();
        }

        List<ExerciseLogData> exerciseLogDatas = new ArrayList<>();

        for (ExerciseLog exerciseLog : exerciseLogs) {
            exerciseLogDatas.add(exerciseLogToExerciseLogData(exerciseLog));
        }

        return exerciseLogDatas;
    }

    @Override
    public ExerciseLogData exerciseLogToExerciseLogData(ExerciseLog exerciseLog) {
        if (exerciseLog == null) {
            return null;
        }

        ExerciseLogData exerciseLogData = new ExerciseLogData();

        exerciseLogData.setId(exerciseLog.getId());
        exerciseLogData.setExerciseData(ExerciseMapper.INSTANCE.exerciseToExerciseData(exerciseLog.getExercise()));
        exerciseLogData.setReps1(exerciseLog.getReps1());
        exerciseLogData.setReps2(exerciseLog.getReps2());
        exerciseLogData.setReps3(exerciseLog.getReps3());
        exerciseLogData.setWeight1(exerciseLog.getWeight1());
        exerciseLogData.setWeight2(exerciseLog.getWeight2());
        exerciseLogData.setWeight3(exerciseLog.getWeight3());
        exerciseLogData.setDate(exerciseLog.getDate());

        return exerciseLogData;
    }


}
