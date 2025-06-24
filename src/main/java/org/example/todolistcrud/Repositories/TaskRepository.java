package org.example.todolistcrud.Repositories;

import org.example.todolistcrud.Models.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    public Optional findByTitle(String title);
    public List<Task> findAll();
    public Optional findById(long id);
    public List<Task> findByCompletedIsTrue();
    public List<Task> findByCompletedIsFalse();

}
