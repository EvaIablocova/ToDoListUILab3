package org.example.todolistuilab3.serviceClient;

import org.example.todolistuilab3.DTOs.TaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;

import java.util.List;

@Service
public class TaskServiceClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String taskServiceUrl = "http://todoback:3010/api/tasks";

    public List<TaskDTO> getAllTasks() {
        ResponseEntity<TaskDTO[]> response = restTemplate.getForEntity(taskServiceUrl, TaskDTO[].class);
        return Arrays.asList(response.getBody());
    }

    public TaskDTO getTaskById(Long id) {
        return restTemplate.getForObject(taskServiceUrl + "/" + id, TaskDTO.class);
    }

    public TaskDTO createTask(TaskDTO taskDTO) {
        return restTemplate.postForObject(taskServiceUrl + "/create", taskDTO, TaskDTO.class);
    }

    public void updateTask(Long id, TaskDTO taskDTO) {
        restTemplate.put(taskServiceUrl + "/update/" + id, taskDTO);
    }

    public void deleteTask(Long id) {
        restTemplate.delete(taskServiceUrl + "/delete/" + id);
    }
}