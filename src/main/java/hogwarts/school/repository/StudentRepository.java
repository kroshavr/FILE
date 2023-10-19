package hogwarts.school.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import hogwarts.school.model.Student;
import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findStudentByAgeBetween(int min, int max);
    Student findStudentById (long id);
}
