/**
 * 
 */
package com.mplus.core.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.alibaba.fastjson.JSON;
import com.mplus.modules.sys.entity.Org;
import com.mplus.modules.sys.service.OrgService;

/**
 * @author wuwj
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class OrgControllerTest {

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
	public void testAddRoot() throws Exception {
		Org org = new Org();
		org.setOrgCode("0");
		org.setOrgName("根节点");
		org.setParentId(null);
		String jsonOrg = JSON.toJSONString(org);
		this.mvc.perform(post("/org/add").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonOrg))
				.andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
	}

	@Test
	public void testAdd() throws Exception {
		Org root = orgService.findOneByCode("0");
		Org org = new Org();
		org.setOrgCode("001300");
		org.setOrgName("莫克信息");
		org.setParentId(root.getId());
		String jsonOrg = JSON.toJSONString(org);
		this.mvc.perform(post("/org/add").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonOrg))
				.andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
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
