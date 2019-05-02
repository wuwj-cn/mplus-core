package com.mplus.core.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mplus.enums.RuleCode;
import com.mplus.modules.sys.service.CodeRuleService;

@Component
public class TaskSchedulerService {
	private static final Logger logger = LoggerFactory.getLogger(TaskSchedulerService.class);
	
	@Autowired
	private CodeRuleService codeRuleService;
	
//	@Scheduled(cron="0 */1 * * * ?") 
    public void executeTask() {
        Thread current = Thread.currentThread();  
        Date now = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String serial = codeRuleService.getSerial(RuleCode.USER);
		logger.info(current.getName() + ", serial:" + serial + ", 当前时间：" + df.format(now));
    }
}
