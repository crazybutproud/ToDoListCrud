package org.example.todolistcrud.Services;

import jakarta.transaction.Transactional;
import org.example.todolistcrud.Models.Task;
import org.example.todolistcrud.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService { // логирование???????
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) { //лучшая практика вместо @Autowired на поле
        this.taskRepository = taskRepository;
    }

    @Transactional
    public Task createNewTask(Task task) { // Сохраняет новую задачу в БД.
        if (taskRepository.findByTitle(task.getTitle()).isPresent()) {
            throw new RuntimeException("Задача с таким названием уже существует!");
        } else {
            return taskRepository.save(task);
        }
    }

    public List<Task> getAllTasks() { //Возвращает все задачи из БД.
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(long id) {
        return taskRepository.findById(id);
    }

    public Optional<Task> getByTitle(String title) {
        return taskRepository.findByTitle(title);
    }

    public List<Task> getTasksByCompletedIsTrue() {
        return taskRepository.findByCompletedIsTrue();
    }

    public List<Task> getTasksByCompletedIsFalse() {
        return taskRepository.findByCompletedIsFalse();
    }

    @Transactional
    public Task updateTask(Task task) {
        if (!taskRepository.existsById(task.getId())) {
            throw new RuntimeException("Задача не найдена!");
        }
        return taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }
    @Transactional
    public void deleteTaskById(long id) {
        taskRepository.deleteById(id);
    }
}
