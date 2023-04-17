package com.training.messaging.controller;

import com.training.messaging.entity.SchedulerTasks;
import com.training.messaging.service.HackerNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/scheduler")
public class HackerNewsController {

    @Autowired
    private SchedulerTasks schedulerTasks;

    @Autowired
    private HackerNewsService hackerNewsService;

    @Autowired
    private ScheduledAnnotationBeanPostProcessor postProcessor;

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public ResponseEntity<String> startScheduler() {
        try {
            schedulerTasks.startScheduler();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Scheduler started.", HttpStatus.OK);
    }

    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    public String stopScheduler() {
        postProcessor.postProcessBeforeDestruction(schedulerTasks, "schedulerTasks");
        return "Scheduler started.";
    }
}
