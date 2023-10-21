package hogwarts.school;

import hogwarts.school.controller.StudentController;
import hogwarts.school.model.Faculty;
import hogwarts.school.model.Student;
import hogwarts.school.repository.StudentRepository;
import hogwarts.school.service.StudentService;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest (StudentController.class)
public class StudentControllerMvcTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentRepository studentRepository;
    @SpyBean
    private StudentService studentService;
    @InjectMocks
    private StudentController studentController;

    @Test
    public void testGetStudent() throws Exception {

        Long id = 1L;
        String name = "testName";
        int age = 18;

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    public void testNewStudent() throws Exception {

        Long id = 110L;
        String name = "testName";
        int age = 18;

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    public void testUpdateStudent() throws Exception {

        Long id = 1L;
        String name = "testName";
        int age = 18;

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student/{id}", id)
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    public void testDeleteStudent() throws Exception {
        Long id = 1L;
        String name = "testName";
        int age = 18;

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/" + id))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetStudentsByAgeBetween() throws Exception {
        int min = 12;
        int max = 15;

        Student firstStudent = new Student();
        firstStudent.setId(1L);
        firstStudent.setName("Student1");
        firstStudent.setAge(14);

        Student secondStudent = new Student();
        secondStudent.setId(2L);
        secondStudent.setName("Student2");
        secondStudent.setAge(12);

        Collection<Student> testStudents = new ArrayList<>();
        testStudents.add(firstStudent);
        testStudents.add(secondStudent);

        when(studentRepository.findStudentByAgeBetween(any(int.class), any(int.class))).thenReturn(testStudents);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/byAgeBetween?min=" + min + "&max=" + max))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Student1"))
                .andExpect(jsonPath("$[1].name").value("Student2"));
    }

    @Test
    public void testGetFacultyOfStudent() throws Exception {
        Long id = 111L;
        String name = "testName";
        int age = 101;
        String color = "testColor";
        Long idOfFaculty = 1L;

        Faculty faculty = new Faculty();
        faculty.setId(idOfFaculty);
        faculty.setName(name);
        faculty.setColor(color);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        student.setFaculty(faculty);

        when(studentRepository.findStudentById(any(Long.class))).thenReturn(student);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/getFacultyByID/", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(idOfFaculty));
    }
}