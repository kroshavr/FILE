package hogwarts.school.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import hogwarts.school.model.Faculty;
import hogwarts.school.model.Student;
import hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Optional;

@RequestMapping("/faculty")
@RestController
public class FacultyController {
    public final FacultyService facultyService;
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }
    @PostMapping
    public ResponseEntity<Faculty> createFaculty (@RequestBody Faculty faculty) {
        return new ResponseEntity<>(facultyService.createFaculty(faculty), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFacultyById (@PathVariable Long id) {
        Faculty faculty = facultyService.getFacultyById(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }
    @PutMapping
    public ResponseEntity<Faculty> updatingFaculty (@RequestBody Faculty faculty) {
        return new ResponseEntity<>(facultyService.updatingFaculty(faculty), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Faculty> updatingFaculty (@PathVariable Long id, @RequestBody Faculty faculty) {
        return new ResponseEntity<>(facultyService.updatingFaculty(id, faculty), HttpStatus.OK);
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<Faculty> deleteFaculty (@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("byNameOrColor/")
    public ResponseEntity getFacultyByNameOrColor(@RequestParam(required = false) String name, @RequestParam(required = false) String color) {
        return ResponseEntity.ok(facultyService.findFacultiesByNameOrColor(name, color));
    }
    @GetMapping("studentsByID/{id}")
    public Collection<Student> getStudentsByIdOfFaculty(@PathVariable Long id) {
        return facultyService.getStudentsOfFaculty(id);
    }

    @GetMapping("/longName")
    public ResponseEntity<Optional<String>> getLongNameFaculty() {
        return ResponseEntity.ok(facultyService.getLongNameFaculty());
    }
}