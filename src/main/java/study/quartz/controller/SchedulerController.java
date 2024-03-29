package study.quartz.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.quartz.dto.SchedulerDto;
import study.quartz.service.SchedulerService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/api/scheduler")
public class SchedulerController {

    private final SchedulerService schedulerService;

    /**
     * 스케줄링 등록
     */
    @PostMapping
    public Boolean registerJob(@RequestBody @Validated SchedulerDto.Req.Register dto) {
        log.info("### SchedulerController.registerJob");
        return schedulerService.registerJob(dto.getJobName(), dto.getGroupName(), dto.getCronExpression(), dto.getType());
    }

    /**
     * 스케줄링 업데이트
     * 주기만 바꾸기 때문에, 업데이트 로직을 사용하기보단-> 삭제 후 생성을 추천
     */
    @PutMapping
    public Boolean updateJob(@RequestBody @Validated SchedulerDto.Req.Update dto) {
        log.info("### SchedulerController.updateJob");
        return schedulerService.updateJob(dto.getJobName(), dto.getGroupName(), dto.getCronExpression());
    }

    /**
     * 스케줄링 삭제
     */
    @DeleteMapping
    public Boolean deleteJob(@RequestBody @Validated SchedulerDto.Req.Delete dto) {
        log.info("### SchedulerController.deleteJob");
        return schedulerService.deleteJob(dto.getJobName(), dto.getGroupName());
    }
}
