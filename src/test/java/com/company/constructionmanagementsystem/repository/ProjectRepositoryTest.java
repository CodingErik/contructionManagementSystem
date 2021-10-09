package com.company.constructionmanagementsystem.repository;

import com.company.constructionmanagementsystem.model.Project;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProjectRepositoryTest {
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
    public void addFindDeleteProject() {

        MathContext mathContext = new MathContext(4);

        Project project = new Project();
        LocalDate deadline = LocalDate.now();
        LocalDate startDate = LocalDate.now();

        project.setName("Project One");
        project.setDeadline(deadline);
        project.setStartDate(startDate);
        project.setRoomType("Kitchen");
        project.setPlumbing(true);
        project.setMaterialBudget(new BigDecimal(2000.00).round(mathContext));
        project.setLaborBudget(new BigDecimal(1000.00).round(mathContext));
        project.setTotalBudget(new BigDecimal(3000.00).round(mathContext));
        project.setStatus("Finished");
        // add
        project = projectRepository.save(project);

        assertEquals(1, projectRepository.findAll().size());

        // find
        Project project1 = projectRepository.findById(project.getId()).get();
        project1.setMaterialBudget(project1.getMaterialBudget().round(mathContext));
        project1.setLaborBudget(project1.getLaborBudget().round(mathContext));
        project1.setTotalBudget(project1.getTotalBudget().round(mathContext));

        assertEquals(project1, project);

        // delete
        projectRepository.deleteById(project.getId());

        assertEquals(0, projectRepository.findAll().size());

    }

    @Test
    public void findAllProjects() {

        MathContext mathContext = new MathContext(4);
        LocalDate deadline = LocalDate.now();
        LocalDate startDate = LocalDate.now();

        Project project1 = new Project();

        project1.setName("Project One");
        project1.setDeadline(deadline);
        project1.setStartDate(startDate);
        project1.setRoomType("Kitchen");
        project1.setPlumbing(true);
        project1.setMaterialBudget(new BigDecimal(2000.00).round(mathContext));
        project1.setLaborBudget(new BigDecimal(1000.00).round(mathContext));
        project1.setTotalBudget(new BigDecimal(3000.00).round(mathContext));
        project1.setStatus("Finished");
        projectRepository.save(project1);

        Project project2 = new Project();

        project2.setName("Project One");
        project2.setDeadline(deadline);
        project2.setStartDate(startDate);
        project2.setRoomType("Kitchen");
        project2.setPlumbing(true);
        project2.setMaterialBudget(new BigDecimal(2000.00).round(mathContext));
        project2.setLaborBudget(new BigDecimal(1000.00).round(mathContext));
        project2.setTotalBudget(new BigDecimal(3000.00).round(mathContext));
        project2.setStatus("Finished");
        projectRepository.save(project2);

        // findAll
        assertEquals(2, projectRepository.findAll().size());
    }

    @Test
    public void saveAndFlushProject() {
        MathContext mathContext = new MathContext(4);

        Project project = new Project();
        LocalDate deadline = LocalDate.now();
        LocalDate startDate = LocalDate.now();

        project.setName("Project One");
        project.setDeadline(deadline);
        project.setStartDate(startDate);
        project.setRoomType("Kitchen");
        project.setPlumbing(true);
        project.setMaterialBudget(new BigDecimal(2000.00).round(mathContext));
        project.setLaborBudget(new BigDecimal(1000.00).round(mathContext));
        project.setTotalBudget(new BigDecimal(3000.00).round(mathContext));
        project.setStatus("In progress");

        project = projectRepository.save(project);

        project.setStatus("Finished");

        // saveAndFlush
        projectRepository.saveAndFlush(project);

        Project project1 = projectRepository.findById(project.getId()).get();
        project1.setMaterialBudget(project1.getMaterialBudget().round(mathContext));
        project1.setLaborBudget(project1.getLaborBudget().round(mathContext));
        project1.setTotalBudget(project1.getTotalBudget().round(mathContext));

        assertEquals(project1, project);
    }
}