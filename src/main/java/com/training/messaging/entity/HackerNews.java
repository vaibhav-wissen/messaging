package com.training.messaging.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Component
public class HackerNews {
    @NonNull
    private Integer id;
    private Boolean deleted;
    private String type;
    private String by;
    private String time;
    private String text;
    private Boolean dead;
    private Long parent;
    private Long poll;
    private List<Long> kids;
    private String url;
    private Integer score;
    private String title;
    private List<Long> parts;
    private Integer descendants;
}
