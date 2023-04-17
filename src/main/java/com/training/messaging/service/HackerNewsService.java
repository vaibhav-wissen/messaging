package com.training.messaging.service;

import com.training.messaging.entity.HackerNews;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class HackerNewsService {

    private final String TOP_STORIES = "https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty";
    private final String STORY = "https://hacker-news.firebaseio.com/v0/item/%d.json?print=pretty";

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    @Value("${spring.application.from}")
    private String from;

    @Value("${spring.application.to}")
    private String to;

//    @Value("${spring.application.cron-delay}")
//    private Long crontime;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EmailService service;

    public void getTopNews() {
        try {
            log.info("Started processing news.");
            ResponseEntity<Object> responseObj = restTemplate.getForEntity(TOP_STORIES, Object.class);
            List<Integer> storiesId = (List<Integer>) responseObj.getBody();
            Map<String, Object> topStoriesMap = new HashMap<>();

            topStoriesMap.put("name", "Vaibhav Malviya");
            topStoriesMap.put("location", "Bangalore");
//            topStoriesMap.put("crontime", (crontime / 60));


            log.debug("Stories Link: {}", storiesId);

            List<HackerNews> topStoriesList = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                HackerNews hackerNews = restTemplate.getForObject(String.format(STORY, storiesId.get(i)), HackerNews.class);

                // Setting Indian timezone.
                sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
                hackerNews.setTime(sdf.format(new Date(Long.parseLong(hackerNews.getTime()) * 1000)));
                topStoriesList.add(hackerNews);

                log.debug("Story: {}", hackerNews);
            }

            topStoriesMap.put("topStoriesList", topStoriesList);

            service.send(to, from, String.format("Top stories from HackerNews - %s", sdf.format(new Date())), topStoriesMap);
            log.info("Completed processing news.");

        } catch (Exception e) {
            log.error("Some exception occured : Check logs");
        }

    }
}
