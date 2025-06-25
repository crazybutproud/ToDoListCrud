package org.example.todolistcrud.Controllers;

import ch.qos.logback.core.model.Model;
import org.example.todolistcrud.Models.Task;
import org.example.todolistcrud.Services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/") // основная страница с кнопками
    public String mainPage() {
        return "/MainPage";
    }

    @GetMapping("/AllTasksPage") //страница со всеми задачами. добавить пагинацию. модель???????????
    public List<Task> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return tasks;
    }

    @GetMapping("/TaskPage") // страница с одной задачей. модель??????
    public String taskPage(Model model) { // возможность найти таск по айди и по имени
//        Task task = taskService.getTaskById();
        return null;
    }
}
