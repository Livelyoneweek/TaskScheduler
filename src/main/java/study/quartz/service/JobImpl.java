package study.quartz.service;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.time.LocalDateTime;

@Slf4j
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class JobImpl implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("### SampleJob.execute");

        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String type = jobDataMap.getString("key");
        // type 별로 여러 작업을 추가...
        if ("printGroupName".equals(type)) {
            String groupName = context.getJobDetail().getKey().getGroup();
            System.out.println("Group Name: " + groupName);
        } else if ("printCurrentTime".equals(type)) {
            System.out.println("Current Time: " + LocalDateTime.now());
        }
    }
}
