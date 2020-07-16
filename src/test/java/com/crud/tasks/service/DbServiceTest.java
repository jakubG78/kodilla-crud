package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    DbService dbService;

    @Mock
    TaskRepository repository;

    @Test
    public void shouldGetEmptyTaskList() {
        //Given
        when(repository.findAll()).thenReturn(new ArrayList<>());

        //When
        List<Task> resultList = dbService.getAllTasks();

        //Then
        assertNotNull(resultList);
        assertEquals(0, resultList.size());
    }

    @Test
    public void shouldGetTaskList() {
        //Given
        Task task = new Task(1L, "test_task", "task_for_test");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        when(repository.findAll()).thenReturn(taskList);

        //When
        List<Task> resultList = dbService.getAllTasks();

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

        resultList.forEach(thisTask -> {
            assertEquals(1L, thisTask.getId(), 0);
            assertEquals("test_task", thisTask.getTitle());
            assertEquals("task_for_test", thisTask.getContent());
        });
    }

    @Test
    public void shouldGetTask() {
        //Given
        Long id = 1L;
        Task task = new Task(1L, "test_task", "task_for_test");
        when(repository.findById(id)).thenReturn(Optional.of(task));

        //When
        Task resultTask = dbService.getTask(id).orElse(task);

        //Then
        assertNotNull(resultTask);
        assertEquals(1L, resultTask.getId(), 0);
        assertEquals("test_task", resultTask.getTitle());
        assertEquals("task_for_test", resultTask.getContent());
    }

    @Test
    public void shouldSaveTask(){
        //Given
        Task task = new Task(1L, "test_task", "task_for_test");
        when(repository.save(task)).thenReturn(task);

        //When
        Task resultTask = dbService.saveTask(task);

        //Then
        assertNotNull(resultTask);
        assertEquals(1L, resultTask.getId(),0);
    }

}