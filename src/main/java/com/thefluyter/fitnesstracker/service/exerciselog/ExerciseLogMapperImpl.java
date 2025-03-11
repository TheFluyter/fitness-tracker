package com.thefluyter.fitnesstracker.service.exerciselog;

import com.thefluyter.fitnesstracker.model.exerciselog.ExerciseLog;
import com.thefluyter.fitnesstracker.model.exerciselog.ExerciseLogData;

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


}
