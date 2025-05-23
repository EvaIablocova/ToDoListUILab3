package org.example.todolistuilab3.controllers;

import org.example.todolistuilab3.DTOs.EmailDTO;
import org.example.todolistuilab3.DTOs.TaskDTO;
import org.example.todolistuilab3.serviceClient.TaskServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/ui/tasks")
public class UiRestController {

    private static final Logger logger = LoggerFactory.getLogger(UiRestController.class);

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

    @PostMapping("/send_email")
    @ResponseBody
    public String sendEmail(@RequestBody EmailDTO emailDTO) {
        logger.info("Запрос на отправку email: {}", emailDTO);
        return taskServiceClient.sendEmail(emailDTO);
    }

    @GetMapping("/check_emails_imap")
    @ResponseBody
    public List<String> checkEmailsUsingIMAP() throws Exception {
        logger.info("Запрос для проверки писем через IMAP");
        return taskServiceClient.checkEmailsUsingIMAP();
    }

    @GetMapping("/check_emails_pop3")
    @ResponseBody
    public String checkEmailsUsingPOP3() throws Exception {
        logger.info("Запрос для проверки писем через POP3");
        return taskServiceClient.checkEmailsUsingPOP3();
    }
}