package hogwarts.school.repository;
import hogwarts.school.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findFacultyByNameIgnoreCaseOrColorIgnoreCase (String name, String color);
}