package com.todolist.controller;

import com.todolist.service.ShowCompleteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CompleteController {
    private final ShowCompleteService showCompleteService;

    public CompleteController(ShowCompleteService showCompleteService) {
        this.showCompleteService = showCompleteService;
    }

    @GetMapping("/complete")
    public String showComplete(Model model) {
        model.addAttribute("tasks", showCompleteService.getComplete());
        return "complete";
    }

    @PostMapping("/complete/update")
    public String revertCompleteTasks(
            @RequestParam(required = false) List<String> taskIds,
            RedirectAttributes redirectAttributes) {

        if (taskIds != null) {
            for (String taskId : taskIds) {
                showCompleteService.changeTask(taskId);
            }
        }

        redirectAttributes.addFlashAttribute("message", "선택한 할 일이 미완료 상태로 변경되었습니다!");

        return "redirect:/complete";
    }
}
