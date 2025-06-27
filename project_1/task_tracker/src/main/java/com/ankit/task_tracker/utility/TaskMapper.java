package com.ankit.task_tracker.utility;

import com.ankit.task_tracker.dtos.TaskRequestDto;
import com.ankit.task_tracker.dtos.TaskResponseDto;
import com.ankit.task_tracker.entities.Task;

public interface TaskMapper {
    TaskResponseDto toDto(Task task);

    Task toEntity(TaskRequestDto taskRequestDto);
}
