package com.sparta.schedule.service;

import com.sparta.schedule.dto.ResponseMessageDto;
import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    private ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


    public ResponseMessageDto createSchedule(ScheduleRequestDto requestDto) {

        Schedule schedule = new Schedule(requestDto);
        scheduleRepository.save(schedule);

        return new ResponseMessageDto(HttpStatus.OK, "일정이 등록되었습니다.", new ScheduleResponseDto(schedule));
    }


    public List<ScheduleResponseDto> getSchedules() {

        return scheduleRepository.findAllByOrderByModifiedAtDesc().stream()
            .map(ScheduleResponseDto::new).toList();
    }

    public ResponseMessageDto selectSchedule(Long id) {
        try {
            Schedule schedule = findSchedule(id);

            return new ResponseMessageDto(HttpStatus.OK, "선택한 일정입니다.", new ScheduleResponseDto(schedule));
        }catch (IllegalArgumentException e){
            return new ResponseMessageDto(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @Transactional
    public ResponseMessageDto updateSchedule(Long id, String password, ScheduleRequestDto requestDto) {
        try {
            Schedule schedule = findSchedule(id);
            validatePassword(id, password);
            schedule.update(requestDto);
            return new ResponseMessageDto(HttpStatus.OK, "일정이 수정되었습니다.", new ScheduleResponseDto(schedule));
        }catch (IllegalArgumentException e){
            return new ResponseMessageDto(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    public ResponseMessageDto deleteSchedule(Long id, String password) {
        try {
            Schedule schedule = findSchedule(id);
            validatePassword(id, password);
            scheduleRepository.delete(schedule);
            return new ResponseMessageDto(HttpStatus.OK, "일정이 삭제되었습니다.", new ScheduleResponseDto(schedule));
        } catch (IllegalArgumentException e) {
            return new ResponseMessageDto(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }


    private void validatePassword(Long id, String password) {
        Schedule schedule = findSchedule(id);
        if (!password.equals(schedule.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");

        }

    }

    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->
            new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }
}
