package hogwarts.school.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import hogwarts.school.model.Faculty;
import hogwarts.school.model.Student;
import hogwarts.school.repository.FacultyRepository;
import java.util.*;

@Service

public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Запущен метод создания факультета");
        return facultyRepository.save(faculty);
    }
    public Faculty getFacultyById(long id) {
        logger.info("Faculty verification method launched");
        return facultyRepository.findById(id).get();
    }
    public Faculty updatingFaculty(Faculty faculty) {
        logger.info("Запущен метод обновления данных факультета");
        return facultyRepository.save(faculty);
    }
    public Faculty updatingFaculty (long id, Faculty faculty){
        logger.info("Запущен метод обновления данных факультета");
        Faculty faculty1 = new Faculty();
        faculty1.setId(id);
        faculty1.setName(faculty.getName());
        faculty1.setColor(faculty.getColor());
        return facultyRepository.save(faculty1);
    }
    public void deleteFaculty ( long id){
        logger.info("Запущен метод удаления факультета");
        facultyRepository.deleteById(id);
    }
    public Collection<Faculty> findFacultiesByNameOrColor(String name, String color) {
        logger.info("Запущен метод поиска факультетов по названию или цвету");
        return facultyRepository.findFacultyByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }
    public Collection<Student> getStudentsOfFaculty(long id) {
        logger.info("Запущен метод поиска студентов факультета");
        return facultyRepository.findById(id).get().getStudents();
    }

    public Optional<String> getLongNameFaculty() {
        logger.info("Запущен метод поиска самого длинного названия факультета");
        return facultyRepository.findAll().stream().parallel()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length));
    }
}