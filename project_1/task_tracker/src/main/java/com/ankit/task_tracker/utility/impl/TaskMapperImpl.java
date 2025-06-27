package com.ankit.task_tracker.utility.impl;

import com.ankit.task_tracker.dtos.TaskRequestDto;
import com.ankit.task_tracker.dtos.TaskResponseDto;
import com.ankit.task_tracker.entities.Task;
import com.ankit.task_tracker.utility.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public TaskResponseDto toDto(Task task) {
        TaskResponseDto responseDto=new TaskResponseDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate(),
                task.getCreatedAt()
        );
        return responseDto;
    }

    @Override
    public Task toEntity(TaskRequestDto taskRequestDto) {
        Task task=new Task();
        task.setTitle(taskRequestDto.getTitle());
        task.setDescription(taskRequestDto.getDescription());
        task.setStatus(taskRequestDto.getStatus());
        task.setPriority(taskRequestDto.getPriority());
        task.setDueDate(taskRequestDto.getDueDate());
        return task;
    }
}
