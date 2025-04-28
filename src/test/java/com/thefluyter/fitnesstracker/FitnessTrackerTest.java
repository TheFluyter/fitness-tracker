package com.thefluyter.fitnesstracker;

import com.thefluyter.fitnesstracker.shared.user.User;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.transaction.annotation.Transactional;

@ComponentScan(basePackageClasses = FitnessTrackerApplication.class)
@Transactional
@DirtiesContext
@TestPropertySource(locations = "classpath:application.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {
    "/integrationtest/sql/exercise.sql",
    "/integrationtest/sql/exercise_log.sql",
    "/integrationtest/sql/user.sql",
    "/integrationtest/sql/user_exercises.sql",
    "/integrationtest/sql/user_exercise_logs.sql"
})
public class FitnessTrackerTest {

    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";
    private static final Long USER_ID = 1L;

    public RequestPostProcessor userAuth() {
        UsernamePasswordAuthenticationToken userAuth;
        User user = new User(USERNAME, PASSWORD);
        user.setId(USER_ID);
        userAuth = new UsernamePasswordAuthenticationToken(user, PASSWORD, user.getAuthorities());
        return SecurityMockMvcRequestPostProcessors.authentication(userAuth);
    }

    public Long getUserId() {
        return USER_ID;
    }
}
