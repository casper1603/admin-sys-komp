package agh.edu.pl.project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
class ProjectApplicationTests {

	@Test
	void contextLoads() {
	}

}
