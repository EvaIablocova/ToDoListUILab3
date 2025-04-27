package org.example.todolistuilab3.controllers;

import org.example.todolistuilab3.DTOs.EmailDTO;
import org.example.todolistuilab3.DTOs.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    private static final Logger logger = LoggerFactory.getLogger(UiController.class);

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

    @GetMapping("/dto/{id}")
    @ResponseBody
    public ResponseEntity<TaskDTO> getTaskDTOById(@PathVariable Long id, Model model) {
        logger.info("Запрос для получения задачи с id: {}", id);
        try {
            TaskDTO task = uiRestController.getTaskById(id);
            if (task == null) {
                logger.warn("Задача с id {} не найдена", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(task);
        } catch (Exception e) {
            logger.error("Ошибка при получении задачи с id {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public String createTask(@RequestBody TaskDTO taskDTO) {
        logger.info("Запрос на создание задачи: {}", taskDTO);
        uiRestController.createTask(taskDTO);
        logger.info("Задача создана: {}", taskDTO);
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

    @PostMapping("/send_email")
    @ResponseBody
    public String sendEmail(@RequestBody EmailDTO emailDTO) {
        logger.info("Запрос на отправку email: {}", emailDTO);
        return uiRestController.sendEmail(emailDTO);
    }

    @GetMapping("/check_emails_imap")
    @ResponseBody
    public List<String> checkEmailsUsingIMAP() throws Exception {
        logger.info("Запрос для проверки писем через IMAP");
        return uiRestController.checkEmailsUsingIMAP();
    }

    @GetMapping("/check_emails_pop3")
    @ResponseBody
    public String checkEmailsUsingPOP3() throws Exception {
        logger.info("Запрос для проверки писем через POP3");
        return uiRestController.checkEmailsUsingPOP3();
    }
}