package org.example.todolistcrud.Controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.example.todolistcrud.Models.Task;
import org.example.todolistcrud.Services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/") // основная страница с кнопками
    public String mainPage(Model model) {
        model.addAttribute("task", new Task()); // Пустой объект для форм
        return "MainPage";
//        return "/MainPage";
    }

    @GetMapping("/AllTasks") //страница со всеми задачами. добавить пагинацию.
    public String getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model
    ) {
        Page<Task> tasks = taskService.getAllTasks(PageRequest.of(page, size));
        model.addAttribute("tasks", tasks);
        return "AllTasks";
    }

    @GetMapping("/Task") // страница с одной задачей.
    public String taskPage(
            @RequestParam Long id,
            Model model
    ) {
        Optional<Task> task = taskService.getTaskById(id);
        task.ifPresent(t -> model.addAttribute("task", t));
        return "TaskPage";
    }

    @PostMapping("/NewTask") //создание новой задачи
    public String newTaskPage(
            @ModelAttribute("task") Task task,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "MainPage"; // Возврат с ошибками
        }
        taskService.createNewTask(task);
        return "redirect:/api/tasks/AllTasks";
    }

    @PostMapping("/Search") // поиск задачи по айди и по названию
    public String searchTask(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long id,
            Model model
    ) {
        if (id != null) {
            Optional<Task> task = taskService.getTaskById(id);
            task.ifPresent(t -> model.addAttribute("tasks", List.of(t)));
        } else if (title != null) {
            Optional<Task> task = taskService.getByTitle(title);
            task.ifPresent(t -> model.addAttribute("tasks", List.of(t)));
        }
        return "AllTasks"; // Используем тот же шаблон, что и для списка
    }
}
