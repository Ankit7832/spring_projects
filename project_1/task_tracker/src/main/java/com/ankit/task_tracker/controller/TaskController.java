package com.ankit.task_tracker.controller;

import com.ankit.task_tracker.dtos.TaskRequestDto;
import com.ankit.task_tracker.dtos.TaskResponseDto;
import com.ankit.task_tracker.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskRequestDto requestDto){
        TaskResponseDto dto = taskService.createTask(requestDto);
        return ResponseEntity.status(201).body(dto);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<?> updateTask(@RequestBody TaskRequestDto requestDto, @PathVariable UUID taskId){
        TaskResponseDto dto=taskService.updateTask(requestDto,taskId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<?> getTask(@PathVariable UUID taskId){
        TaskResponseDto dto= taskService.getTask(taskId);
        return ResponseEntity.ok(dto);
    }
    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable UUID taskId){
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<TaskResponseDto>> getAllTaskPaginatedAndSorted( @RequestParam(defaultValue = "0") int page,
                                                                               @RequestParam(defaultValue = "10") int pageSize,
                                                                               @RequestParam(defaultValue = "createdAt") String sortBy,
                                                                               @RequestParam(defaultValue = "desc") String direction){
        Page<TaskResponseDto> responseDtoPage=taskService.getAllTaskPaginatedAndSorted(page,pageSize,sortBy,direction);
        return ResponseEntity.ok(responseDtoPage);
    }

}
