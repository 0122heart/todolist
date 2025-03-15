package com.todolist.service;

import com.todolist.repository.TaskRepository;
import com.todolist.model.Task;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class ShowWillDoService {
    private final TaskRepository taskRepository;

    public ShowWillDoService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(Task task) {
        taskRepository.addTask(task);
    }

    public List<Task> getWill() {
        return taskRepository.getWillTasks();
    }

    public void changeTask(String ID) {
        taskRepository.changeTask(ID);
    }
}
