package com.mplus.core.scheduler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTaskService {

	@Async
	public void executeAsyncTask(Integer i) {
		System.out.println("执行异步任务: " + i);
	}
}
