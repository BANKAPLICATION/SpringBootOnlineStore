package SpringMyProject.SpringMyProject.repositories;

import SpringMyProject.SpringMyProject.entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {
}
