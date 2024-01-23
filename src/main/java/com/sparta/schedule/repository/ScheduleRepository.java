package com.sparta.schedule.repository;

import com.sparta.schedule.entity.Schedule;
import java.sql.PreparedStatement;
import java.sql.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Schedule save(Schedule schedule) {
        //DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체


        String sql = "INSERT INTO schedule (title, contents,author,password) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update( con -> {
                PreparedStatement preparedStatement = con.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);

                preparedStatement.setString(1, schedule.getTitle());
                preparedStatement.setString(2, schedule.getContents());
                preparedStatement.setString(3, schedule.getAuthor());
                preparedStatement.setString(4, schedule.getPassword());
                return preparedStatement;
            },
            keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);

        return schedule;

    }
}
