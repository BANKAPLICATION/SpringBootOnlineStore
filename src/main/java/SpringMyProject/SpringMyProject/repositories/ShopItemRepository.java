package SpringMyProject.SpringMyProject.repositories;


import SpringMyProject.SpringMyProject.entities.ShopItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ShopItemRepository extends JpaRepository <ShopItems, Long> {
    List<ShopItems> findAllByPriceGreaterThanAndAmountLessThan(double price, int amount);
    List<ShopItems> findByCategories_Id(Long id);
    List<ShopItems> findByNameContainingIgnoreCase(String keyword);
}
