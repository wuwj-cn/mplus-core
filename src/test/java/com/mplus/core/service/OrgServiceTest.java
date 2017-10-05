package com.mplus.core.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.mplus.core.entity.Org;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class OrgServiceTest {

	@Autowired
	private OrgService orgService;
	
	@Test
	public void testSaveRoot() {
		Org root = new Org();
		root.setOrgCode("0");
		root.setOrgName("ROOT");
		root.setParentOrgId(null);
		orgService.saveOrg(root);
	}
	
	public void testSaveOrg() {
		Org org = new Org();
		org.setOrgCode("001100");
		org.setOrgName("莫克工作室");
		org.setParentOrgId(null);
		orgService.saveOrg(org);
		
		Org child1 = new Org();
		child1.setOrgCode("001101");
		child1.setOrgName("研发组");
		child1.setParentOrgId(org.getOrgId());
		orgService.saveOrg(child1);
		
		Org child2 = new Org();
		child2.setOrgCode("001102");
		child2.setOrgName("产品组");
		child2.setParentOrgId(org.getOrgId());
		orgService.saveOrg(child2);
	}

	@Test
	public void testFindOrgsByParent() {
		Org parent = orgService.findOneByCode("0");
		List<Org> orgs = orgService.findOrgsByParent(parent.getOrgId());
		assertNotNull(orgs);
	}

}
