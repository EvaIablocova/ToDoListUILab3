package org.example.todolistuilab3.controllers;

import org.example.todolistuilab3.DTOs.TaskDTO;
import org.example.todolistuilab3.serviceClient.TaskServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/ui/tasks")
public class UiRestController {

    // Создание логгера
    private static final Logger logger = LoggerFactory.getLogger(TaskServiceClient.class);

    private final TaskServiceClient taskServiceClient;

    @Autowired
    public UiRestController(TaskServiceClient taskServiceClient) {
        this.taskServiceClient = taskServiceClient;
    }

    @GetMapping
    public List<TaskDTO> getAllTasks() {
        logger.info("Запрос для получения всех задач");
        List<TaskDTO> tasks = taskServiceClient.getAllTasks();
        logger.info("Получено {} задач", tasks.size());
        return tasks;
    }

    @GetMapping("/{id}")
    public TaskDTO getTaskById(@PathVariable Long id) {
        logger.info("Запрос для получения задачи с id: {}", id);
        TaskDTO task = taskServiceClient.getTaskById(id);
        logger.info("Задача с id {} получена: {}", id, task);
        return task;
    }

    @PostMapping("/create")
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO) {
        logger.info("Запрос на создание задачи: {}", taskDTO);
        TaskDTO task = taskServiceClient.createTask(taskDTO);
        logger.info("Задача создана: {}", task);
        return task;
    }

    @PutMapping("/update/{id}")
    public void updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        logger.info("Запрос на обновление задачи с id {}: {}", id, taskDTO);
        taskServiceClient.updateTask(id, taskDTO);
        logger.info("Задача с id {} обновлена", id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id) {
        logger.info("Запрос на удаление задачи с id: {}", id);
        taskServiceClient.deleteTask(id);
        logger.info("Задача с id {} удалена", id);
    }
}

