package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
 public class ScheduleService {
    private ScheduleRepository scheduleRepository;
    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {

        Schedule schedule = new Schedule(requestDto);
        scheduleRepository.save(schedule);
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }


    public List<ScheduleResponseDto> getSchedules() {

        return scheduleRepository.findAllByOrderByModifiedAtDesc().stream().map(ScheduleResponseDto::new).toList();

    }
    public Schedule selectSchedule(Long id) {
        return findSchedule(id);
    }
    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, String password, ScheduleRequestDto requestDto) {
        Schedule schedule = findSchedule(id);
        // 입력한 비밀번호와 저장된 비밀번호가 일치하는지 확인
        if (!password.equals(schedule.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        schedule.update(requestDto);
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
        return scheduleResponseDto;
    }

    public  Long deleteSchedule(Long id, String password) {
        Schedule schedule = findSchedule(id);
        // 입력한 비밀번호와 저장된 비밀번호가 일치하는지 확인
        if (!password.equals(schedule.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        scheduleRepository.delete(schedule);
        return id;
    }




    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->
            new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }
}
