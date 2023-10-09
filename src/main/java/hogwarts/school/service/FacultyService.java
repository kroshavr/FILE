package hogwarts.school.service;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hogwarts.school.model.Faculty;
import hogwarts.school.model.Student;
import hogwarts.school.repository.FacultyRepository;
import java.util.*;
@Service
@NoArgsConstructor
public class FacultyService {
    private FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }
    public Faculty getFacultyById(long id) {
        return facultyRepository.findById(id).get();
    }
    public Faculty updatingFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }
    public Faculty updatingFaculty (long id, Faculty faculty){
        Faculty faculty1 = new Faculty();
        faculty1.setId(id);
        faculty1.setName(faculty.getName());
        faculty1.setColor(faculty.getColor());
        return facultyRepository.save(faculty1);
    }
    public void deleteFaculty ( long id){
        facultyRepository.deleteById(id);
    }
    public Collection<Faculty> findFacultiesByNameOrColor(String name, String color) {
        return facultyRepository.findFacultyByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }
    public Collection<Student> getStudentsOfFaculty(long id) {
        return facultyRepository.findById(id).get().getStudents();
    }
}