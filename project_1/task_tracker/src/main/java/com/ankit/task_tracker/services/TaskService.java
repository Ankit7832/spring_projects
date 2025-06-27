package com.ankit.task_tracker.services;

import com.ankit.task_tracker.dtos.TaskRequestDto;
import com.ankit.task_tracker.dtos.TaskResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface TaskService {

    TaskResponseDto createTask(TaskRequestDto requestDto);

    TaskResponseDto updateTask(TaskRequestDto requestDto, UUID taskId);

    TaskResponseDto getTask(UUID taskId);

    void deleteTask(UUID taskId);

    Page<TaskResponseDto> getAllTaskPaginatedAndSorted(int page, int pageSize, String sortBy, String direction);
}
