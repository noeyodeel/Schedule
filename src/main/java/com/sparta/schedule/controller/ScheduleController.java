package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.service.ScheduleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ScheduleController{
    private ScheduleService scheduleService;
    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }


    @PostMapping("/schedule")   //일정 입력
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto){
        return scheduleService.createSchedule(requestDto);
    }
    @GetMapping("/schedules")   //일정 전체 다보여주기
    public List<ScheduleResponseDto> getSchedule(){
        return scheduleService.getSchedules();
    }
    @GetMapping("/schedules/{id}")  //선택한 일정 보여주기
    public Schedule selectSchedule(@PathVariable Long id) {
        return scheduleService.selectSchedule(id);
    }
    @PutMapping("/schedules/{id}")  //선택한 일정 수정
    public Long updateSchedule(@PathVariable Long id,@RequestBody ScheduleRequestDto requestDto){
        return scheduleService.updateSchedule(id,requestDto);

    }

    @DeleteMapping("/schedule/{id}")    //선택한 일정 삭제
    public Long deleteSchedule(@PathVariable Long id,@RequestBody ScheduleRequestDto requestDto){
       return scheduleService.deleteSchedule(id);
    }




}
