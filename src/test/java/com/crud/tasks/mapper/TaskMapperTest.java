package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloCardDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

}
