package study.taskSchedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ScheduledFuture;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ScheduleService {

    private final ThreadPoolTaskScheduler taskScheduler;

    private ScheduledFuture<?> job;

    public void startSchedule(String message, long period) {
        this.job = taskScheduler.scheduleWithFixedDelay(createRunnable(message), period);
    }

    public void stopSchedule() {
        if (job != null) {
            job.cancel(true);
        }
    }

    private Runnable createRunnable(String message) {
        return () -> {
            System.out.println(message);
        };
    }
}
