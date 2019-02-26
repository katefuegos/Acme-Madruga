
package repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Finder;
import domain.Procession;

@Repository
public interface FinderRepository extends JpaRepository<Finder, Integer> {

	@Query("select m.finder from Member m where m.id=?1")
	Finder findByMemberId(int memberId);

	@Query("select p from Procession p where p.ticker=?1 or p.title=?1 or p.description.id=?1 and p.moment>=?2 and p.moment<=?3")
	Page<Procession> searchProcessions(String keyword, Date dateMin, Date dateMax, Pageable pageable);
}
