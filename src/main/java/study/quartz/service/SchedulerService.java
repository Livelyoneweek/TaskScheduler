package study.quartz.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchedulerService {

    private final Scheduler scheduler;

    public Boolean scheduleJob(String jobName, String groupName, String cronExpression, String type) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("key", type);

        JobDetail jobDetail = JobBuilder.newJob(SampleJob.class)
                .withIdentity(jobName, groupName)
                .usingJobData(jobDataMap)
                .build();

        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobName, groupName)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
            return true;
        } catch (SchedulerException schedulerException) {
            log.error("", schedulerException);
            return false;
        }
    }
}
