/**
 * 
 */
package com.mplus.core.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mplus.core.advice.Result;
import com.mplus.core.entity.Org;
import com.mplus.core.service.OrgService;
import com.mplus.enums.DataState;

/**
 * @author wuwj
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class OrgControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(OrgControllerTest.class);

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private OrgService orgService;

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		mvc = webAppContextSetup(context).build();
	}

	@Test
	public void testAdd() throws Exception {
		Org root = orgService.findOneByCode("0");
		Org org = new Org();
		org.setOrgCode("001300");
		org.setOrgName("莫克信息");
		org.setParentOrgId(root.getOrgId());
		String jsonOrg = JSON.toJSONString(org);
		String result = this.mvc.perform(post("/org/add").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonOrg))
				.andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
		 ScriptEngineManager em = new ScriptEngineManager();
		 ScriptEngine engine = em.getEngineByName("javascript");
		 engine.eval("var o = " + result + "; var data = o.data;");
		 Object data = engine.get("data");
		 logger.info(JSON.toJSONString(data));
		 assertEquals(org.getOrgName(), ((Org)data).getOrgName());
	}

	@Test
	public void testAdd2() throws Exception {
		String requestBody = "{\"orgCode\":\"001301\",\"orgName\":\"001301\",\"parentOrgId\":\"402880e95eecd190015eecd214540000\",\"state\":\"1\"}";
		this.mvc.perform(post("/org/add").contentType(MediaType.APPLICATION_JSON_UTF8).content(requestBody))
				.andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void testGetAll() throws Exception {
		this.mvc.perform(get("/org/getAll").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
	}

	@Test
	public void testGetOneByCode() throws Exception {
		this.mvc.perform(get("/org/getOne/001103").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andDo(print());
	}
}
