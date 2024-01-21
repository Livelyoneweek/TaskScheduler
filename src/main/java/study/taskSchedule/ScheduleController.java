package study.taskSchedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedule")
    public void createSchedule(@RequestParam String message, @RequestParam long period) {
        scheduleService.startSchedule(message, period);
    }

    @DeleteMapping("/schedule")
    public void deleteSchedule() {
        scheduleService.stopSchedule();
    }
}
