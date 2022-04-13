package SpringMyProject.SpringMyProject.repositories;


import SpringMyProject.SpringMyProject.entities.Categories;
import SpringMyProject.SpringMyProject.entities.ShopItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CategoriesRepository extends JpaRepository<Categories, Long> {
}
