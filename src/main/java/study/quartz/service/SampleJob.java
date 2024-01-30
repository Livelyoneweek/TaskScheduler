package study.quartz.service;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;

@Slf4j
public class SampleJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String type = jobDataMap.getString("key");

        // 작업 내용...
        if ("printGroupName".equals(type)) {
            String groupName = context.getJobDetail().getKey().getGroup();
            System.out.println("Group Name: " + groupName);
        } else if ("printCurrentTime".equals(type)) {
            System.out.println("Current Time: " + LocalDateTime.now());
        }
    }
}