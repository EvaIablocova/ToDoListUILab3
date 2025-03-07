package org.example.todolistuilab3.DTOs;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class TaskDTO {

    private Long id;

    private String title;
    private String description;

    private TaskStatus status;

    private Integer priority; // 1 - высокий, 3 - низкий
    private String assignedTo;

    public TaskDTO() {
    }

    public TaskDTO(Long id, String title, String description, TaskStatus status, Integer priority, String assignedTo) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.assignedTo = assignedTo;
    }
}
