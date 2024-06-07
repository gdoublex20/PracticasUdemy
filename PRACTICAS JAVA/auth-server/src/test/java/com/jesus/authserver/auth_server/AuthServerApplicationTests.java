package com.jesus.authserver.auth_server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, value = "9000")
@AutoConfigureMockMvc
class AuthServerApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void test() throws Exception {

	}

}
