# 동적 스케줄링 작업

## ~~1.ThreadPoolTaskScheduler 사용~~
## 2.Quartz  라이브러리 사용(Job-persistence)

### API guide
<br/> 
http://localhost:8080/v1/api/scheduler  (POST method)

```
{
    "jobName": "jobName1",
    "groupName" : "groupName",
    "cronExpression" : "*/20 * * * * ?",
    "type" : "printGroupName" //printGroupName, printCurrentTime
}
```
<br/> 
다른 메소드는 SchedulerController 참고
