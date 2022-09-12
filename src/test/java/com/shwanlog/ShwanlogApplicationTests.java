package com.shwanlog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShwanlogApplicationTests {

	@Test
	void contextLoads() {
		String regex = "([a-z])([A-Z]+)";
		String replacement = "$1_$2";
		System.out.println("CamelCaseToSomethingElse"
				.replaceAll(regex, replacement)
				.toLowerCase());
	}

}
