package com.mplus.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mplus.core.base.repo.BaseRepository;
import com.mplus.core.base.service.impl.BaseServiceImpl;
import com.mplus.modules.sys.entity.Module;
import com.mplus.modules.sys.repo.ModuleRepsitory;
import com.mplus.modules.sys.service.ModuleService;

@Service
@Transactional
public class ModuleServiceImpl extends BaseServiceImpl<Module, String> implements ModuleService {

	@Autowired
	private ModuleRepsitory moduleRepository;
	
	@Override
	public BaseRepository<Module, String> getRepository() {
		return moduleRepository;
	}

	

}
