package com.dmainali.veggievault;


import com.dmainali.veggievault.controller.VeggieController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VeggievaultApplicationTests {

	@Autowired
	private VeggieController controller;

	/**
	 * Test method to verify that the Spring context loads correctly and the controller object is not null.
	 */
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
