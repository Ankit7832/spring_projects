package com.ankit.task_tracker.utility;
import com.ankit.task_tracker.entities.Task;
import com.ankit.task_tracker.entities.TaskPriority;
import com.ankit.task_tracker.entities.TaskStatus;
import com.ankit.task_tracker.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final TaskRepository taskRepository;
    private final Random random = new Random();

    @Override
    public void run(String... args) {
        if (taskRepository.count() == 0) {
            System.out.println("🌱 Seeding 20 random tasks...");

            for (int i = 1; i <= 20; i++) {
                Task task = new Task();

                task.setTitle("Task #" + i);
                task.setDescription("Auto-generated task " + i);
                task.setStatus(randomStatus());
                task.setPriority(randomPriority());
                task.setCreatedAt(LocalDateTime.now().minusDays(random.nextInt(10)));
                task.setDueDate(LocalDateTime.now().plusDays(random.nextInt(10) + 1));

                taskRepository.save(task);
            }

            System.out.println("✅ Task seeding complete.");
        }
    }

    private TaskStatus randomStatus() {
        TaskStatus[] statuses = TaskStatus.values();
        return statuses[random.nextInt(statuses.length)];
    }

    private TaskPriority randomPriority() {
        TaskPriority[] priorities = TaskPriority.values();
        return priorities[random.nextInt(priorities.length)];
    }
}
