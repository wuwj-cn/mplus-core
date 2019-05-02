package com.mplus.modules.sys.repo;

import org.springframework.stereotype.Repository;

import com.mplus.core.base.repo.BaseRepository;
import com.mplus.modules.sys.entity.Module;

@Repository
public interface ModuleRepsitory extends BaseRepository<Module, String> {

}
