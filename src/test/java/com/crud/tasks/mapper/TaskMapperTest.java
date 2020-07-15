package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest {

    @Autowired
    TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(15L, "Test task #1", "Test mapping TaskDto to Task domain object");

        //When
        Task mappedTask = taskMapper.mapToTask(taskDto);

        //Then
        Assert.assertNotNull(mappedTask);
        Assert.assertEquals(15L, mappedTask.getId(), 0);
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(11L, "Test task #2", "Test mapping Task to TaskDto object");

        //When
        TaskDto mappedTaskDto = taskMapper.mapToTaskDto(task);

        //Then
        Assert.assertNotNull(mappedTaskDto);
        Assert.assertEquals("Test task #2", mappedTaskDto.getTitle());
        System.out.println(mappedTaskDto);
    }

    @Test
    public void testToTaskDtoList() {
        //Given
        Task task1 = new Task(15L, "Test task #1", "Test mapping TaskDto to Task domain object");
        Task task2 = new Task(11L, "Test task #2", "Test mapping Task to TaskDto object");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);

        //When
        List<TaskDto> mappedTaskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        Assert.assertEquals(2, mappedTaskDtoList.size());
        Assert.assertEquals(11L, taskList.get(1).getId(), 0);
        Assert.assertEquals("Test task #1", taskList.get(0).getTitle());
    }
}
