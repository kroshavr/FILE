package hogwarts.school;

import hogwarts.school.controller.StudentController;
import hogwarts.school.model.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private StudentController studentController;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }
    @Test
    public void testGetStudent() throws Exception {
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/" + 1, String.class))
                .isNotNull();
    }
    @Test
    public void testNewStudent() throws Exception {
        Student student = new Student();
        student.setName("Student");
        student.setAge(22);

        Assertions
                .assertThat(this.testRestTemplate.postForObject("http://localhost:" + port + "/student/", student, String.class))
                .isNotNull();
    }

    @Test
    public void testUpdateStudent() throws Exception {
        Student student = new Student();
        student.setName("test");
        student.setAge(22);
        student.setId(10L);

        ResponseEntity<Student> studentEntity = testRestTemplate.exchange("http://localhost:" + port + "/student",
                HttpMethod.PUT, new HttpEntity<>(student), Student.class);
    }

    @Test
    public void testDeleteStudent() throws Exception {
        this.testRestTemplate.delete("http://localhost:" + port + "/student/" + 10);
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/10", String.class))
                .doesNotContain("id");
    }

    @Test
    public void testGetStudentsByAgeBetween() throws Exception {
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/byAgeBetween?fromAge=11&toAge=14", String.class))
                .isNotNull();
    }

    @Test
    public void testGetFacultyOfStudent() throws Exception {
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/getFacultyByID/1", String.class))
                .isNotNull();
    }
}