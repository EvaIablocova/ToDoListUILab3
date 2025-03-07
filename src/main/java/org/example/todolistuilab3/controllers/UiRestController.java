package org.example.todolistuilab3.controllers;

import org.example.todolistuilab3.DTOs.TaskDTO;
import org.example.todolistuilab3.serviceClient.TaskServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ui/tasks")
public class UiRestController {

    private final TaskServiceClient taskServiceClient;

    @Autowired
    public UiRestController(TaskServiceClient taskServiceClient) {
        this.taskServiceClient = taskServiceClient;
    }

    @GetMapping
    public List<TaskDTO> getAllTasks() {
        return taskServiceClient.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskDTO getTaskById(@PathVariable Long id) {
        return taskServiceClient.getTaskById(id);
    }

    @PostMapping("/create")
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO) {
        return taskServiceClient.createTask(taskDTO);
    }

    @PutMapping("/update/{id}")
    public void updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        taskServiceClient.updateTask(id, taskDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskServiceClient.deleteTask(id);
    }
}

