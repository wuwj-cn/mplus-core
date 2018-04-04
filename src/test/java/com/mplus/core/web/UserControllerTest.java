package com.mplus.core.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

	private MockMvc mvc;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setUp() throws Exception {
		mvc = webAppContextSetup(context).build();
	}

	@Test
	public void testAdd() throws Exception {
		// {"userName": "wuwj", "password": "123455", "org": {"orgCode": "001300"}, "uState": "0"}
		String requestBody = "{\"userName\": \"wuwj\", \"password\": \"123455\", \"org\": {\"orgCode\": \"001300\"}}";
		this.mvc.perform(post("/user/add")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(requestBody))
				.andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	public void testGetOne() {
	}

	@Test
	public void testGetUsersByOrg() {
	}

}
