package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private long id;
    private String title;
    private String contents;
    private String author;
    private LocalDateTime crateddAt;
    private LocalDateTime modifiedAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.author = schedule.getAuthor();
        this.crateddAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }
}
