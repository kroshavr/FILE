package hogwarts.school;

import hogwarts.school.controller.FacultyController;
import hogwarts.school.model.Faculty;
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
public class FacultyControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private FacultyController facultyController;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
    }
    @Test
    public void testGetFaculty() throws Exception {
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/faculty/" + 1, String.class))
                .isNotNull();
    }
    @Test
    public void testNewFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("test");
        faculty.setColor("red");

        Assertions
                .assertThat(this.testRestTemplate.postForObject("http://localhost:" + port + "/faculty/", faculty, String.class))
                .isNotNull();
    }

    @Test
    public void testUpdateFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("test");
        faculty.setColor("red");


        ResponseEntity<Faculty> facultyEntity = testRestTemplate.exchange("http://localhost:" + port + "/faculty",
                HttpMethod.PUT, new HttpEntity<>(faculty), Faculty.class);
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        this.testRestTemplate.delete("http://localhost:" + port + "/faculty/" + 1);
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/faculty/1", String.class))
                .doesNotContain("id");
    }

    @Test
    public void testGetFacultyByNameOrColor() throws Exception {
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/faculty/byNameOrColor?name=test", String.class))
                .isNotNull();
    }

    @Test
    public void testGetStudentsByIdOfFaculty() throws Exception {
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/faculty/studentsByID/2", String.class))
                .isNotNull();
    }
}
