package hogwarts.school;

import hogwarts.school.controller.FacultyController;
import hogwarts.school.model.Faculty;
import hogwarts.school.model.Student;
import hogwarts.school.repository.FacultyRepository;
import hogwarts.school.service.FacultyService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)
public class FacultyControllerMvcTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FacultyRepository facultyRepository;
    @SpyBean
    private FacultyService facultyService;
    @InjectMocks
    private FacultyController facultyController;

    @Test
    public void testGetFaculty() throws Exception {
        Long id = 5L;
        String name = "testName";
        String color = "testColor";

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    public void testNewFaculty() throws Exception {
        Long id = 5L;
        String name = "testName";
        String color = "testColor";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    public void testUpdateFaculty() throws Exception {
        Long id = 5L;
        String name = "testName";
        String color = "testColor";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        Long id = 5L;
        String name = "testName";
        String color = "testColor";

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + id))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetFacultyByNameOrColor() throws Exception {
        String color = "testColor";
        String name = "testName";

        Faculty firstFaculty = new Faculty();
        firstFaculty.setId(5L);
        firstFaculty.setName(name);
        firstFaculty.setColor(color);

        Faculty secondFaculty = new Faculty();
        secondFaculty.setId(6L);
        secondFaculty.setName("second");
        secondFaculty.setColor("red");

        ArrayList<Faculty> testFacultyByColor = new ArrayList<>();
        testFacultyByColor.add(firstFaculty);
        testFacultyByColor.add(secondFaculty);

        ArrayList<Faculty> testFacultyByName = new ArrayList<>();
        testFacultyByName.add(firstFaculty);
        testFacultyByName.add(secondFaculty);

        when(facultyRepository.findFacultyByNameIgnoreCaseOrColorIgnoreCase(null, color)).thenReturn(testFacultyByColor);
        when(facultyRepository.findFacultyByNameIgnoreCaseOrColorIgnoreCase(name, null)).thenReturn(testFacultyByName);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/byNameOrColor?name=" + name + "&color=" + color))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetStudentsByIdOfFaculty() throws Exception {
        String color = "testColor";
        Long id = 10L;
        String name = "testName";

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        Student firstStudent = new Student();
        firstStudent.setId(1L);
        firstStudent.setName("firstStudent");
        firstStudent.setAge(1);
        firstStudent.setFaculty(faculty);

        Student secondStudent = new Student();
        secondStudent.setId(2L);
        secondStudent.setName("secondStudent");
        secondStudent.setAge(2);
        secondStudent.setFaculty(faculty);

        ArrayList<Student> testStudents = new ArrayList<>();
        testStudents.add(firstStudent);
        testStudents.add(secondStudent);
        faculty.setStudents(testStudents);

        when(facultyRepository.getById(any(Long.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/studentsByID/", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("firstStudent"))
                .andExpect(jsonPath("$[1].name").value("secondStudent"));
    }
}
