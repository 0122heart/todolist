package com.todolist.service;

import com.todolist.repository.TaskRepository;
import com.todolist.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowCompleteService {
    private final TaskRepository taskRepository;

    public ShowCompleteService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void changeTask(String taskId) {
        taskRepository.changeTask(taskId);
    }

    public List<Task> getComplete() {
        return taskRepository.getCompleteTasks();
    }
}
