package org.example.todolistcrud.Services;

import jakarta.transaction.Transactional;
import org.example.todolistcrud.Models.Task;
import org.example.todolistcrud.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createNewTask(Task task) { // ?? может по названию
        return taskRepository.save(task);
    }
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    public Task getTaskById(long id) {
        return taskRepository.findById(id);
    }
    public Optional getByTitle(String title) {
        return taskRepository.findByTitle(title);
    }
    public List<Task> getTasksByCompletedIsTrue() {
        return taskRepository.findByCompletedIsTrue();
    }
    public List<Task> getTasksByCompletedIsFalse() {
        return taskRepository.findByCompletedIsFalse();
    }
    public Task updateTask(Task task) { // ?? может по названию
        return taskRepository.save(task);
    }
    public void deleteTask(Task task) {
        taskRepository.delete(task); // ?? может по названию
    }
}
