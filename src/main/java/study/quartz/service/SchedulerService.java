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

    /**
     * 스케줄링 등록
     */
    public Boolean registerJob(String jobName, String groupName, String cronExpression, String type) {
        log.info("### SchedulerService.registerJob");
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("key", type);

        JobDetail jobDetail = JobBuilder.newJob(JobImpl.class)
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

    /**
     * 주기만 바꾸기 때문에, 업데이트 로직을 사용하기보단-> 삭제 후 생성을 추천
     */
    public boolean updateJob(String jobName, String groupName, String cronExpression) {
        log.info("### SchedulerService.updateJob");
        try {
            // 새 Trigger 생성
            CronTrigger newTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(jobName, groupName)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                    .build();

            // 기존 작업의 스케줄 수정
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, groupName);
            scheduler.rescheduleJob(triggerKey, newTrigger);
            return true;
        } catch (SchedulerException schedulerException) {
            log.error("", schedulerException);
            return false;
        }
    }

    public boolean deleteJob(String jobName, String groupName) {
        log.info("### SchedulerService.deleteJob");
        try {
            JobKey jobKey = JobKey.jobKey(jobName, groupName);
            return scheduler.deleteJob(jobKey);
        } catch (SchedulerException schedulerException) {
            log.error("", schedulerException);
            return false;
        }
    }

}
