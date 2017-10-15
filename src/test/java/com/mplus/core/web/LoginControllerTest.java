package com.mplus.core.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.SubjectThreadState;
import org.apache.shiro.util.ThreadState;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LoginControllerTest {

	private MockMvc mvc;
	private ThreadState _threadState;
	protected Subject _mockSubject;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setUp() throws Exception {
		mvc = webAppContextSetup(context).build();
		_mockSubject = Mockito.mock(Subject.class);
        _threadState = new SubjectThreadState(_mockSubject);
        _threadState.bind();
	}
	
	@Test
	public void testLogin() throws Exception {
		// {"userName": "wuwj", "password": "123455", "rememberMe": "false"}
		String requestBody = "{\"username\": \"wuwj\", \"password\": \"123455\", \"rememberMe\": \"false\"}";
		this.mvc.perform(post("/login")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(requestBody))
				.andExpect(status().isOk())
				.andDo(print());
	}

}
