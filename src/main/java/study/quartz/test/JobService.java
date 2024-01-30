package study.quartz.test;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class JobService implements Job {

    public void execute(JobExecutionContext context) {
        System.out.println("Hello World!");
    }

    public static void main(String[] args) {
        try {
            // 스케줄러 팩토리 생성
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();

            // 스케줄러 인스턴스 생성
            Scheduler scheduler = schedulerFactory.getScheduler();

            // Job 생성
            JobDetail job = JobBuilder.newJob(JobService.class).withIdentity("job1", "group1").build();

            // Trigger 생성 (10초마다 반복)
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                    .startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever())
                    .build();

            // 스케줄러에 Job과 Trigger 추가
            scheduler.scheduleJob(job, trigger);

            // 스케줄러 실행
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
