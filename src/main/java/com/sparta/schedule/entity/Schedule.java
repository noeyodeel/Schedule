package com.sparta.schedule.entity;

import com.sparta.schedule.dto.ScheduleRequestDto;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Schedule extends Timestamped{
    private long id;
    private String title;
    private String contents;
    private String author;
    private String password;




    public Schedule(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.author = requestDto.getAuthor();
        this.password = requestDto.getPassword();
    }
}
