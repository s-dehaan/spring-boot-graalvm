package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void shouldReturnStatusOK() {
		ResponseEntity<String> response = restTemplate.getForEntity("/", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		String status = documentContext.read("$.status");
		assertThat(status).isEqualTo("OK");
	}
}
