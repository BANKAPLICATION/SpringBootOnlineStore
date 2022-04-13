package SpringMyProject.SpringMyProject.repositories;


import SpringMyProject.SpringMyProject.entities.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CountryRepository extends JpaRepository<Countries, Long> {

}
