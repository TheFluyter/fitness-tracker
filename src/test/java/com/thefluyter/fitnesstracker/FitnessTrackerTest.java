package com.thefluyter.fitnesstracker;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@ComponentScan(basePackageClasses = FitnessTrackerApplication.class)
@Transactional
@DirtiesContext
@TestPropertySource(locations = "classpath:application.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {
    "/integrationtest/sql/exercise.sql",
    "/integrationtest/sql/exercise_log.sql"
})
public class FitnessTrackerTest {
}
