package study.quartz.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.quartz.dto.SchedulerDto;
import study.quartz.service.SchedulerService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/api/scheduler")
public class SchedulerController {

    private final SchedulerService schedulerService;

    @PostMapping
    public Boolean register(@RequestBody @Validated SchedulerDto.Req.Register dto) {
        log.info("### SchedulerController.register");
        return schedulerService.scheduleJob(dto.getJobName(), dto.getGroupName(), dto.getCronExpression(), dto.getType());
    }
}
