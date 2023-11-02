package hogwarts.school.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import hogwarts.school.model.Faculty;
import hogwarts.school.model.Student;
import hogwarts.school.service.StudentService;
import java.util.Collection;

@RequestMapping("/student")
@RestController
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping
    public ResponseEntity<Student> newStudent (@RequestBody Student student) {
        return new ResponseEntity<>(studentService.newStudent(student), HttpStatus.OK);
    }
    @GetMapping ("/{id}")
    public ResponseEntity<Student> getStudentById (@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }
    @PutMapping
    public ResponseEntity<Student> updatingStudentData (@RequestBody Student student) {
        return new ResponseEntity<>(studentService.updatingStudentData(student), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Student> updatingStudentData (@PathVariable Long id, @RequestBody Student student) {
        return new ResponseEntity<>(studentService.updatingStudentData(id, student), HttpStatus.OK);
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<Student> deleteStudentData (@PathVariable Long id) {
        studentService.deleteStudentData(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("byAgeBetween")
    public Collection<Student> findStudents(@RequestParam int min, @RequestParam int max) {
        return studentService.findByAgeBetween(min, max);
    }
    @GetMapping("getFacultyByID/{id}")
    public ResponseEntity<Faculty> getFacultyOfStudent(@PathVariable Long id) {
        Faculty faculty = studentService.getFacultyOfStudent(id);
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("getNumberOfAllStudents")
    public Integer getNumberOfAllStudents() {
        return studentService.getNumberOfAllStudents();
    }

    @GetMapping("getAvgAgeStudents")
    public Integer getAvgAgeStudents() {
        return studentService.getAvgAgeStudents();
    }

    @GetMapping("getFiveLastStudents")
    public Collection<Student> getFiveLastStudents() {
        return studentService.getFiveLastStudents();
    }

    @GetMapping("/nameStart")
    public Collection<String> findStudentByNameStartA() {
        return studentService.findStudentByNameStart();
    }

    @GetMapping("/avgAgeStudents")
    public double findStudentsByAvgAge() {
        return studentService.findStudentsByAvgAge();
    }
}