package com.mplus.core.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import org.apache.shiro.subject.Subject;
import org.junit.After;
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

import com.mplus.AbstractShiroTest;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LoginControllerTest extends AbstractShiroTest {

	private MockMvc mvc;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setUp() throws Exception {
		mvc = webAppContextSetup(context).build();
	}

//	@Test
	public void testLogin() throws Exception {
		// 1. Create a mock authenticated Subject instance for the test to run:
		Subject subjectUnderTest = createNiceMock(Subject.class);
		expect(subjectUnderTest.isAuthenticated()).andReturn(true);

		// 2. Bind the subject to the current thread:
		setSubject(subjectUnderTest);

		// {"userName": "wuwj", "password": "123455", "rememberMe": "false"}
		String requestBody = "{\"username\": \"wuwj\", \"password\": \"123455\", \"rememberMe\": \"false\"}";
		String result = this.mvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON_UTF8).content(requestBody))
										.andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
		assertNotNull(result);;
	}

//	@After
	public void tearDownSubject() {
		// 3. Unbind the subject from the current thread:
		clearSubject();
	}
}
