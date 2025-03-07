package org.example.todolistuilab3.controllers;

import org.example.todolistuilab3.DTOs.TaskDTO;
import org.example.todolistuilab3.serviceClient.TaskServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class UiController {

    private final UiRestController uiRestController;

    @Autowired
    public UiController(UiRestController uiRestController) {
        this.uiRestController = uiRestController;
    }

    @GetMapping
    public String getAllTasks(Model model) {
        List<TaskDTO> tasks = uiRestController.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @GetMapping("/{id}")
    public String getTaskById(@PathVariable Long id, Model model) {
        TaskDTO task = uiRestController.getTaskById(id);
        model.addAttribute("task", task);
        return "oneTask";
    }

    @PostMapping("/create")
    public String createTask(@RequestBody TaskDTO taskDTO) {
        TaskDTO task = uiRestController.createTask(taskDTO);
        return "redirect:/tasks";
    }

    @PutMapping("/update/{id}")
    public String updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        uiRestController.updateTask(id, taskDTO);
        return "redirect:/tasks";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        uiRestController.deleteTask(id);
        return "redirect:/tasks";
    }
}
