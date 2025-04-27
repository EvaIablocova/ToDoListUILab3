package org.example.todolistuilab3.serviceClient;

import org.example.todolistuilab3.DTOs.EmailDTO;
import org.example.todolistuilab3.DTOs.TaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TaskServiceClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String taskServiceUrl = "http://todoback:3010/api/tasks";

    private static final Logger logger = LoggerFactory.getLogger(TaskServiceClient.class);

    public List<TaskDTO> getAllTasks() {
        logger.info("Запрос для получения всех задач из: {}", taskServiceUrl);
        ResponseEntity<TaskDTO[]> response = restTemplate.getForEntity(taskServiceUrl, TaskDTO[].class);
        logger.info("Получено {} задач", response.getBody().length);
        return Arrays.asList(response.getBody());
    }

    public TaskDTO getTaskById(Long id) {
        logger.info("Запрос для получения задачи с id: {}", id);
        TaskDTO task = restTemplate.getForObject(taskServiceUrl + "/" + id, TaskDTO.class);
        logger.info("Задача с id {} получена: {}", id, task);
        return task;
    }

    public TaskDTO createTask(TaskDTO taskDTO) {
        logger.info("Запрос на создание задачи: {}", taskDTO);
        TaskDTO task = restTemplate.postForObject(taskServiceUrl + "/create", taskDTO, TaskDTO.class);
        logger.info("Задача создана: {}", task);
        return task;
    }

    public void updateTask(Long id, TaskDTO taskDTO) {
        logger.info("Запрос на обновление задачи с id {}: {}", id, taskDTO);
        restTemplate.put(taskServiceUrl + "/update/" + id, taskDTO);
        logger.info("Задача с id {} обновлена", id);
    }

    public void deleteTask(Long id) {
        logger.info("Запрос на удаление задачи с id: {}", id);
        restTemplate.delete(taskServiceUrl + "/delete/" + id);
        logger.info("Задача с id {} удалена", id);
    }

    public String sendEmail(EmailDTO emailDTO) {
        logger.info("Запрос на отправку email: {}", emailDTO);
        String response = restTemplate.postForObject(taskServiceUrl + "/send_email", emailDTO, String.class);
        logger.info("Результат отправки email: {}", response);
        return response;
    }

    public List<String> checkEmailsUsingIMAP() {
        logger.info("Запрос для проверки писем через IMAP");
        ResponseEntity<String[]> response = restTemplate.getForEntity(taskServiceUrl + "/check_emails_imap", String[].class);
        logger.info("Получено {} писем через IMAP", response.getBody().length);
        return Arrays.asList(response.getBody());
    }

    public String checkEmailsUsingPOP3() {
        logger.info("Запрос для проверки писем через POP3");
        String response = restTemplate.getForObject(taskServiceUrl + "/check_emails_pop3", String.class);
        logger.info("Получено последнее письмо через POP3: {}", response);
        return response;
    }
}