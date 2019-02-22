
package repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import domain.Finder;
import domain.Procession;

@Repository
public interface FinderRepository extends JpaRepository<Finder, Integer> {

	@Query("select m.finder from Member m where m.id=?1")
	Finder findByMemberId(int memberId);

	@Query("select f from Procession f where (f.ticker.ticker like %:keyword% or f.description like %:keyword% or f.adress like %:keyword%) and (f.area = (select a from Area a join a.name k where (key(k) like %:nameArea%))) and (f.deadline BETWEEN :dateMin and :dateMax)")
	Page<Procession> searchProcessions(@Param("keyword") String keyword, @Param("nameArea") String nameArea, @Param("dateMin") Date dateMin, @Param("dateMax") Date dateMax, Pageable pageable);

}
