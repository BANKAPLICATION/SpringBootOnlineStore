package SpringMyProject.SpringMyProject.repositories;

import SpringMyProject.SpringMyProject.entities.Orders;
import SpringMyProject.SpringMyProject.entities.ShopItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {

}
