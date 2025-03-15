package com.todolist.controller;

import com.todolist.model.Task;
import com.todolist.service.ShowWillDoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class WillController {
    private final ShowWillDoService showWillDoService;

    public WillController(ShowWillDoService showWillDoService) {
        this.showWillDoService = showWillDoService;
    }

    @GetMapping("/will")
    public String showWill(Model model) {
        model.addAttribute("tasks", showWillDoService.getWill());
        return "will";
    }

    @PostMapping("/will")
    public String registerWill(
            @RequestParam String name,
            @RequestParam("dueDate") String dueDateStr,
            RedirectAttributes redirectAttributes) {

        LocalDate dueDate = LocalDate.parse(dueDateStr);
        Task task = new Task(name, dueDate);
        showWillDoService.addTask(task);

        redirectAttributes.addFlashAttribute("message", "할 일이 추가되었습니다!");

        return "redirect:/will";
    }

    @PostMapping("/will/update")
    public String changeWill(
            @RequestParam(required = false) List<String> taskIds,
            RedirectAttributes redirectAttributes) {

        if (taskIds != null) {
            for (String taskId : taskIds) {
                showWillDoService.changeTask(taskId);
            }
        }

        redirectAttributes.addFlashAttribute("tasks", showWillDoService.getWill());

        return "redirect:/will";
    }
}
