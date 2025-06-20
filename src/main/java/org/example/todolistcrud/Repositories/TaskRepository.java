package org.example.todolistcrud.Repositories;

import org.example.todolistcrud.Models.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    public Task findByTitle(String title);
    public List<Task> findAll();
    public Task findById(long id);
    public List<Task> findByCompletedIsTrue();
    public List<Task> findByCompletedIsFalse();

}
