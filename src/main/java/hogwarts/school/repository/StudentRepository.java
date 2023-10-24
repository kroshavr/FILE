package hogwarts.school.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import hogwarts.school.model.Student;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findStudentByAgeBetween(int min, int max);

    Student findStudentById (long id);

    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    Integer getNumberOfAllStudents ();

    @Query(value = "SELECT AVG(age)  FROM student", nativeQuery = true)
    Integer getAvgAgeStudents ();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    Collection<Student> getFiveLastStudents ();
}
