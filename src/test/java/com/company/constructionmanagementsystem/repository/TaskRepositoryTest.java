package com.company.constructionmanagementsystem.repository;

import com.company.constructionmanagementsystem.model.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TaskRepository taskRepository;


    @Before
    public void setUp() throws Exception {

        employeeRepository.deleteAll();

        projectRepository.deleteAll();

        taskRepository.deleteAll();
    }

    @Test
    public void addFindDeleteTask() {
        LocalDate startDate = LocalDate.now();
        LocalDate deadline = LocalDate.now();

        Task task = new Task();
        task.setName("Task One");
        task.setStartDate(startDate);
        task.setDeadline(deadline);
        task.setDescription("This is a task.");
        task.setStatus("In progress");

        // add
        task = taskRepository.save(task);

        assertEquals(1, taskRepository.findAll().size());

        // find
        Task task1 = taskRepository.findById(task.getId()).get();

        assertEquals(task1, task);

        // delete
        taskRepository.deleteById(task.getId());

        assertEquals(0, taskRepository.findAll().size());
    }

    @Test
    public void findAllTasks() {
        LocalDate startDate = LocalDate.now();
        LocalDate deadline = LocalDate.now();

        Task task1 = new Task();
        task1.setName("Task One");
        task1.setStartDate(startDate);
        task1.setDeadline(deadline);
        task1.setDescription("This is task1.");
        task1.setStatus("In progress");

        taskRepository.save(task1);

        Task task2 = new Task();
        task2.setName("Task One");
        task2.setStartDate(startDate);
        task2.setDeadline(deadline);
        task2.setDescription("This is task2.");
        task2.setStatus("In progress");

        taskRepository.save(task2);

        assertEquals(2, taskRepository.findAll().size());

    }

    @Test
    public void saveAndFlushTask() {
        LocalDate startDate = LocalDate.now();
        LocalDate deadline = LocalDate.now();

        Task task = new Task();
        task.setName("Task One");
        task.setStartDate(startDate);
        task.setDeadline(deadline);
        task.setDescription("This is a task.");
        task.setStatus("In progress");
        task = taskRepository.save(task);

        task.setStatus("Finish");

        taskRepository.saveAndFlush(task);

        Task task1 = taskRepository.findById(task.getId()).get();

        assertEquals(task1, task);
    }
}