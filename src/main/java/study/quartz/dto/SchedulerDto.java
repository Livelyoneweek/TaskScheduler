package study.quartz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class SchedulerDto {

    public static class Req {

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Register {
            private String jobName;
            private String groupName;
            private String cronExpression;
            private String type;
        }
    }
}
