package ssvv.example;

import domain.Student;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import repository.StudentXMLRepo;
import service.Service;
import validation.StudentValidator;
import validation.ValidationException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestAddStudent {
    private StudentXMLRepo studentFileRepository;
    private StudentValidator studentValidator;
    private Service service;

    @BeforeAll
    static void createXML() {
        File xml = new File("fisiere/studentiTest.xml");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(xml))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                    "<inbox>\n" +
                    "\n" +
                    "</inbox>");
            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void setup() {
        this.studentFileRepository = new StudentXMLRepo("fisiere/studentiTest.xml");
        this.studentValidator = new StudentValidator();
        this.service = new Service(this.studentFileRepository, this.studentValidator, null, null, null, null);
    }

    @AfterAll
    static void removeXML() {
        new File("fisiere/studentiTest.xml").delete();
    }

    @Test
    public void testAddStudentOnGroup() {
        Student newStudent1 = new Student("1", "Ana", 931, "ana@gmail.com");
        Student newStudent2 = new Student("2", "Ana", -6, "ana@gmail.com");
        Student newStudent3 = new Student("3", "Ana", 0, "ana@gmail.com");
        this.service.addStudent(newStudent1);
        assertThrows(ValidationException.class, () -> this.service.addStudent(newStudent2));
        this.service.addStudent(newStudent3);
        var students = this.service.getAllStudenti().iterator();
        assertEquals(students.next(), newStudent1);
//        assertEquals(students.next(), newStudent2);
        assertEquals(students.next(), newStudent3);

        this.service.deleteStudent("1");
        this.service.deleteStudent("3");
    }

    @Test
    public void testAddStudentOnName() {
        Student newStudent1 = new Student("1", "Ana", 931, "ana@gmail.com");
        Student newStudent2 = new Student("2", "", 931, "ana@gmail.com");
        Student newStudent3 = new Student("3", null, 931, "ana@gmail.com");
        this.service.addStudent(newStudent1);
        var students = this.service.getAllStudenti().iterator();
        assertEquals(students.next(), newStudent1);
        assertThrows(ValidationException.class, () -> this.service.addStudent(newStudent2));
        assertThrows(ValidationException.class, () -> this.service.addStudent(newStudent3));

        this.service.deleteStudent("1");
    }


    @Test
    public void testAddStudentOnEmail() {
        Student newStudent1 = new Student("1", "Ana", 931, "ana@gmail.com");
        Student newStudent2 = new Student("2", "Ana", 931, "");
        Student newStudent3 = new Student("3", "Ana", 931, null);
        this.service.addStudent(newStudent1);
        var students = this.service.getAllStudenti().iterator();
        assertEquals(students.next(), newStudent1);
        assertThrows(ValidationException.class, () -> this.service.addStudent(newStudent2));
        assertThrows(ValidationException.class, () -> this.service.addStudent(newStudent3));

        this.service.deleteStudent("1");
    }

    @Test
    public void testAddStudentOnId() {
        Student newStudent1 = new Student("2345", "Ana", 931, "ana@gmail.com");
        Student newStudent2 = new Student("", "Ana", 931, "ana@gmail.com");
        Student newStudent3 = new Student(null, "Ana", 931, "ana@gmail.com");
        assertNull(newStudent3.getID());

        this.service.addStudent(newStudent1);
        var students = this.service.getAllStudenti().iterator();
        assertEquals(students.next(), newStudent1);
        assertThrows(ValidationException.class, () -> this.service.addStudent(newStudent2));
        assertThrows(ValidationException.class, () -> this.service.addStudent(newStudent3));

        this.service.deleteStudent("2345");
    }



}