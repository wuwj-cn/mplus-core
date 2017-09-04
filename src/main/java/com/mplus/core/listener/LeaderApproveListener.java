package com.mplus.core.listener;

import java.util.Collections;
import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mplus.core.service.UserService;
import com.mplus.utils.SpringUtil;

public class LeaderApproveListener implements TaskListener {
	private static final long serialVersionUID = -5924279940765763222L;
	private final static Logger log = LoggerFactory.getLogger(LeaderApproveListener.class);
	
	@Override
	public void notify(DelegateTask task) {
		log.info("领导审核监听器...");
		//设置任务处理候选人
		UserService userService = SpringUtil.getBean(UserService.class);
//		List<String> leaders = userService.getSimpleCheckerByDept(Long.valueOf(task.getVariable("dept").toString()));
		List<String> leaders = Collections.EMPTY_LIST;
		log.info(leaders.toString());
		log.info(task.getVariable("dept").toString());
		task.addCandidateUsers(leaders);
	}

}
