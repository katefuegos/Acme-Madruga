package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Floaat;

@Repository
public interface FloaatRepository extends JpaRepository<Floaat, Integer> {

}
