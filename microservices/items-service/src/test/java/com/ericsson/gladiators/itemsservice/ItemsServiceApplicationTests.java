package com.ericsson.gladiators.itemsservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ItemsServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ItemsServiceApplicationTests {

	@Test
	void contextLoads() throws Exception {
		ItemsServiceApplication.main(new String[] {});
		assertTrue(true);
	}


}
