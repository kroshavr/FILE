package hogwarts.school.repository;

import hogwarts.school.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Avatar findAvatarByStudentId (long id);
}
