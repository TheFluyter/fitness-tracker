package com.thefluyter.fitnesstracker;

import com.thefluyter.fitnesstracker.model.user.User;
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
    "/integrationtest/sql/user.sql"
})
public class FitnessTrackerTest {

    public RequestPostProcessor userAuth() {
        UsernamePasswordAuthenticationToken userAuth;
        User user = new User("user", "password");
        userAuth = new UsernamePasswordAuthenticationToken(user, "password", user.getAuthorities());
        return SecurityMockMvcRequestPostProcessors.authentication(userAuth);
    }
}
