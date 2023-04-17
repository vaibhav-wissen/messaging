package com.training.messaging.entity;

import com.training.messaging.service.HackerNewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
//@EnableScheduling
public class SchedulerTasks {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    private HackerNewsService hackerNewsService;

//    @Value("${spring.application.cron-delay}")
//    private Integer crontime;

//    @Scheduled(fixedDelayString = "${spring.application.cron-delay}000")
    public void startScheduler() {
        hackerNewsService.getTopNews();
        log.info("Current Time: {}",sdf.format(new Date()));
//        log.info("Crontime delay - {} sec, Current Time: {}", this.crontime, sdf.format(new Date()));
    }

}
