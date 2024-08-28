package com.ericsson.gladiators.teammanagerservice.Application;

import com.ericsson.gladiators.teammanagerservice.TeamServiceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {"MYSQL_DB_USERNAME = root"})
@TestPropertySource(properties = {"MYSQL_DB_PASSWORD = root"})
class TeamServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void main() {
		TeamServiceApplication.main(new String[] {});
	}

}
