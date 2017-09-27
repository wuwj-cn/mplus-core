package com.mplus.core.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mplus.core.service.CodeRuleService;

@Component
public class TaskSchedulerService {
	private static final Logger logger = LoggerFactory.getLogger(TaskSchedulerService.class);
	
	@Autowired
	private CodeRuleService codeRuleService;
	
	@Scheduled(cron="0 */1 * * * ?") 
    public void executeTask() {
        Thread current = Thread.currentThread();  
        Date now = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ruleCode = "001";
		String serial = codeRuleService.getSerial(ruleCode);
		logger.info(current.getName() + ", serial:" + serial + ", 当前时间：" + df.format(now));
    }
}
