package com.sparta.schedule.entity;

import com.sparta.schedule.dto.ScheduleRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "schedule")
public class Schedule extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 20)
    private String title;

    @Column(name = "contents", nullable = false, length = 500)
    private String contents;

    @Column(name = "author", nullable = false, length = 10)
    private String author;

    @Column(name = "password", length = 20)
    private String password;

    public Schedule(ScheduleRequestDto requestDto) {
        title = requestDto.getTitle();
        contents = requestDto.getContents();
        author = requestDto.getAuthor();
        password = requestDto.getPassword();
    }


    public void update(ScheduleRequestDto requestDto) {
        title = requestDto.getTitle();
        contents = requestDto.getContents();
        author = requestDto.getAuthor();
    }

}