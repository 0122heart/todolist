package com.todolist.repository;

import com.todolist.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class TaskRepository {
    private final HashMap<Task, Boolean> tasks = new HashMap<>();

    public void addTask(Task task) {
        tasks.put(task, false);
    }

    public List<Task> getWillTasks() {
        List<Task> will = new ArrayList<>();
        for (Task task : tasks.keySet()) {
            if (!tasks.get(task)) {
                will.add(task);
            }
        }
        return will;
    }

    public List<Task> getCompleteTasks() {
        List<Task> complete = new ArrayList<>();
        for (Task task : tasks.keySet()) {
            if (tasks.get(task)) {
                complete.add(task);
            }
        }
        return complete;
    }

    public void changeTask(String Id) {
        for (Task task : tasks.keySet()) {
            if (task.getId().equals(Id)) {
                tasks.put(task, !tasks.get(task)); // 상태 반전
            }
        }
    }
}
