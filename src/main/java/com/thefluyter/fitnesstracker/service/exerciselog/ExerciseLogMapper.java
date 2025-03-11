package com.thefluyter.fitnesstracker.service.exerciselog;

import com.thefluyter.fitnesstracker.model.exerciselog.ExerciseLog;
import com.thefluyter.fitnesstracker.model.exerciselog.ExerciseLogData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExerciseLogMapper {

    ExerciseLogMapper INSTANCE = Mappers.getMapper(ExerciseLogMapper.class);

    ExerciseLog exerciseLogDataToExerciseLog(ExerciseLogData exerciseLogData);

}
