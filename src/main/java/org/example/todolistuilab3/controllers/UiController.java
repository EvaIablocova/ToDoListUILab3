package org.example.todolistuilab3.controllers;

import org.example.todolistuilab3.DTOs.TaskDTO;
import org.example.todolistuilab3.serviceClient.TaskServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class UiController {

    // Создание логгера
    private static final Logger logger = LoggerFactory.getLogger(TaskServiceClient.class);

    private final UiRestController uiRestController;

    @Autowired
    public UiController(UiRestController uiRestController) {
        this.uiRestController = uiRestController;
    }

    @GetMapping
    public String getAllTasks(Model model) {
        logger.info("Запрос для получения всех задач");
        List<TaskDTO> tasks = uiRestController.getAllTasks();
        logger.info("Получено {} задач", tasks.size());
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @GetMapping("/{id}")
    public String getTaskById(@PathVariable Long id, Model model) {
        logger.info("Запрос для получения задачи с id: {}", id);
         TaskDTO task = uiRestController.getTaskById(id);
         model.addAttribute("task", task);
        return "oneTask";
    }

    @PostMapping("/create")
    public String createTask(@RequestBody TaskDTO taskDTO) {
        logger.info("Запрос на создание задачи: {}", taskDTO);
        TaskDTO task = uiRestController.createTask(taskDTO);
        logger.info("Задача создана: {}", task);
        return "redirect:/tasks";
    }

    @PutMapping("/update/{id}")
    public String updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        logger.info("Запрос на обновление задачи с id {}: {}", id, taskDTO);
        uiRestController.updateTask(id, taskDTO);
        logger.info("Задача с id {} обновлена", id);
        return "redirect:/tasks";
    }

    @PutMapping("/updateOne/{id}")
    @ResponseBody
    public ResponseEntity<String> updateTaskFromOne(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        logger.info("Запрос на обновление задачи с id {}: {}", id, taskDTO);
        uiRestController.updateTask(id, taskDTO);
        logger.info("Задача с id {} обновлена", id);
        return ResponseEntity.ok("Task updated");
    }
    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        logger.info("Запрос на удаление задачи с id: {}", id);
        uiRestController.deleteTask(id);
        logger.info("Задача с id {} удалена", id);
        return "redirect:/tasks";
    }
}
