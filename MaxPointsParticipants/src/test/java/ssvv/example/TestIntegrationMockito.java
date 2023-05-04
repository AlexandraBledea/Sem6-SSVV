package ssvv.example;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class TestIntegrationMockito {

    @Mock
    private StudentValidator studentValidator;

    @Mock
    private TemaValidator temaValidator;

    @Mock
    private StudentXMLRepo studentXMLRepository;

    @Mock
    private TemaXMLRepo temaXMLRepository;

    @Mock
    private NotaValidator notaValidator;

    @Mock
    private NotaXMLRepo notaXMLRepository;

    private Service service;

    @BeforeEach
    public void setup() {

        studentValidator = mock(StudentValidator.class);
        temaValidator = mock(TemaValidator.class);
        notaValidator = mock(NotaValidator.class);
        temaXMLRepository = mock(TemaXMLRepo.class);
        studentXMLRepository = mock(StudentXMLRepo.class);
        notaXMLRepository = mock(NotaXMLRepo.class);

        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }


    @Test
    public void testAddStudent(){
        System.out.println("Test student - add");
        Student s1 = new Student("222", "ana", 931, "ana@gmail.com");
        try{
            when(service.addStudent(s1)).thenReturn(null);
        }
        catch (ValidationException e){
            e.printStackTrace();
        }

        try{
            Student s1_test = service.addStudent(s1);
            assertNull(s1_test);
        }
        catch (ValidationException e){
            e.printStackTrace();
        }
    }



    @Test
    public void testAddStudentAndAssignment(){
        System.out.println("Test student and tema - add");
        Student s1 = new Student("222", "ana", 931, "ana@gmail.com");
        Tema tema1 = new Tema("222", "a", 1, 1);

        try{
            when(service.addStudent(s1)).thenReturn(null);
            when(service.addTema(tema1)).thenReturn(null);
        }
        catch (ValidationException e){
            e.printStackTrace();
        }

        try{
            Student s1_test = service.addStudent(s1);
            assertNull(s1_test);
            Tema tema1_test = service.addTema(tema1);
            assertNull(tema1_test);
        }
        catch (ValidationException e){
            e.printStackTrace();
        }
    }


    @Test
    public void testAddStudentAndAssignmentAndGrade(){
        System.out.println("Test student and tema and nota - add");
        Student s1 = new Student("222", "ana", 931, "ana@gmail.com");
        Tema tema1 = new Tema("222", "a", 1, 1);
        Nota nota1 = new Nota("222", "222", "222", 10, LocalDate.now());

//        Student s2 = new Student("333", "ana", 931, "ana@gmail.com");
//        Tema tema2 = new Tema("333", "a", 1, 1);
//        Nota nota2 = new Nota("333", "333", "333", 10, LocalDate.now());

        try{
            when(service.addStudent(s1)).thenReturn(null);
            when(service.addTema(tema1)).thenReturn(null);
            when(service.findTema("222")).thenReturn(tema1);
            when(service.findStudent("222")).thenReturn(s1);
            when(service.addNota(nota1, "ok")).thenReturn(null);
        }
        catch (ValidationException e){
            e.printStackTrace();
        }

        try{
            Student s1_test = service.addStudent(s1);
            assertNull(s1_test);
            Tema tema1_test = service.addTema(tema1);
            assertNull(tema1_test);
            Nota nota1_test = service.addNota(nota1, "ok");
            assertNull(nota1_test);

        }
        catch (ValidationException e){
            e.printStackTrace();
        }
    }

}
