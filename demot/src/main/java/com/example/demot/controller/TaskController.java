package com.example.demot.controller;



import com.example.demot.model.Task;

import com.example.demot.repository.TaskRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task){
        return new ResponseEntity<>(taskRepository.save(task), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Task>> readAll(){
        return new ResponseEntity<>(taskRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> read(@PathVariable Long id){
        return taskRepository.findById(id)
                .map(task -> new ResponseEntity<>(task, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> edit(@RequestBody Task newTask, @PathVariable Long id){
        Task updatedTask = taskRepository.findById(id).map(task -> {
            task.setDate(newTask.getDate());
            task.setDescription(newTask.getDescription());
            return taskRepository.save(task);
        }).orElseGet(() -> {
            newTask.setId(id);
            return taskRepository.save(newTask);
        });
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @PatchMapping("/{id}:mark-as-done")
    public ResponseEntity<Void> updateDone(@PathVariable Long id){
        taskRepository.markAsDone(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        taskRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
