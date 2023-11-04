package hogwarts.school.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import hogwarts.school.model.Faculty;
import hogwarts.school.model.Student;
import hogwarts.school.repository.StudentRepository;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student newStudent(Student student) {
        logger.info("Запущен метод добавления нового студента");
        return studentRepository.save(student);
    }

    public Student getStudentById(long id) {
        logger.info("Запущен метод поиска студента");
        return studentRepository.findById(id).get();
    }

    public Student updatingStudentData(Student student) {
        logger.info("Запущен метод обновления данных студента");
        return studentRepository.save(student);
    }

    public Student updatingStudentData(long id, Student student) {
        logger.info("Запущен метод обновления данных студента");
        Student student1 = new Student();
        student1.setId(id);
        student1.setName(student.getName());
        student1.setAge(student.getAge());
        return studentRepository.save(student1);
    }

    public void deleteStudentData(long id) {
        logger.info("Запущен метод удаления студента");
        studentRepository.deleteById(id);
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("Запущен метод поиска студентов по возрасту в интервале");
        return studentRepository.findStudentByAgeBetween(min, max);
    }

    public Faculty getFacultyOfStudent(long id) {
        logger.info("Запущен метод поиска факультета у студента");
        return studentRepository.findById(id).get().getFaculty();
    }

    public Integer getNumberOfAllStudents() {
        logger.info("Запущен метод получения количества студентов");
        return studentRepository.getNumberOfAllStudents();
    }

    public Integer getAvgAgeStudents() {
        logger.info("Запущен метод получения среднего возраста студентов");
        return studentRepository.getAvgAgeStudents();
    }

    public Collection<Student> getFiveLastStudents() {
        logger.info("Запущен метод получения 5 последних студентов");
        return studentRepository.getFiveLastStudents();
    }

    public Collection<String> findStudentByNameStart() {
        logger.info("Запущен метод получения студентов, чьи имена начинаются с буквы А");
        return studentRepository.findAll().stream()
                .filter(student -> student.getName().toUpperCase().startsWith("А"))
                .map(student -> student.getName().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
    }

    public double findStudentsByAvgAge() {
        logger.info("Запущен метод получения среднего возраста всех студентов");
        return studentRepository.findAll().stream()
                .mapToDouble(Student::getAge)
                .average()
                .getAsDouble();
    }

    public void printStudent (int id) {
        System.out.println(studentRepository.findAll().get(id).getName() + " " + id);
    }
    public void getStudentByDifferentStreams() {
        logger.info("Запущен метод получения имен 6 студентов в разных потоках");
        printStudent(0);
        printStudent(1);

        new Thread(() -> {
            printStudent(2);
            printStudent(3);
        }).start();

        new Thread(() -> {
            printStudent(4);
            printStudent(5);
        }).start();
    }

    public synchronized void printSynchronizedStudent(int id) {
        System.out.println(studentRepository.findAll().get(id).getName() + " " + id);
    }
    public void getStudentBySynchronizedDifferentStreams() {
        logger.info("Запущен метод получения имен 6 студентов в разных синхронизированных потоках");
        printSynchronizedStudent(0);
        printSynchronizedStudent(1);

        new Thread(() -> {
            printSynchronizedStudent(2);
            printSynchronizedStudent(3);
        }).start();

        new Thread(() -> {
            printSynchronizedStudent(4);
            printSynchronizedStudent(5);
        }).start();
    }
}