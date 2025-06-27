package com.ankit.task_tracker.services.impl;

import com.ankit.task_tracker.dtos.TaskRequestDto;
import com.ankit.task_tracker.dtos.TaskResponseDto;
import com.ankit.task_tracker.entities.Task;
import com.ankit.task_tracker.repository.TaskRepository;
import com.ankit.task_tracker.services.TaskService;
import com.ankit.task_tracker.utility.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper mapper;

    @Override
    public TaskResponseDto createTask(TaskRequestDto requestDto) {
        Task task=mapper.toEntity(requestDto);
        task.setCreatedAt(LocalDateTime.now());
        taskRepository.save(task);

        return mapper.toDto(task);
    }

    @Override
    public TaskResponseDto updateTask(TaskRequestDto requestDto, UUID taskId) {
        Task task= taskRepository.findById(taskId).orElseThrow(()-> new RuntimeException("Task doesnt exist with given taskId"));

        task.setTitle(requestDto.getTitle());
        task.setDescription(requestDto.getDescription());
        task.setStatus(requestDto.getStatus());
        task.setPriority(requestDto.getPriority());
        task.setDueDate(requestDto.getDueDate());

        taskRepository.save(task);
        return mapper.toDto(task);
    }

    @Override
    public TaskResponseDto getTask(UUID taskId) {
        Task task= taskRepository.findById(taskId).orElseThrow(()-> new RuntimeException("Task doesnt exist with given taskId"));

        return mapper.toDto(task);
    }

    @Override
    public void deleteTask(UUID taskId) {
        Task task= taskRepository.findById(taskId).orElseThrow(()-> new RuntimeException("Task doesnt exist with given taskId"));
        taskRepository.delete(task);
    }

    @Override
    public Page<TaskResponseDto> getAllTaskPaginatedAndSorted(int page,int pageSize, String sortBy,String direction){
        Sort sort= direction.equalsIgnoreCase("asc")?
                Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(page,pageSize,sort);
        Page<Task> taskPage=taskRepository.findAll(pageable);
        return taskPage.map(mapper::toDto);
    }

}
