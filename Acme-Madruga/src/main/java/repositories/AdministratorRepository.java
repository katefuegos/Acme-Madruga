
package repositories;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.Member;
import domain.Procession;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

	@Query("select h from Administrator h where h.userAccount.id = ?1")
	Administrator findAdministratorByUserAccount(int userAccountId);

	@Query("select a from Actor a where a.userAccount.username=?1")
	Administrator findAdminByUsername(String username);

	///////////
	//DASHBOARD QUERIES
	//////////

	//DASHBOARD C
	//C1 - avg,min,max,stddev of the number of members per brotherhood.
	@Query("select avg(1.0 * (select count(e) from Enrolment e where e.brotherhood.id = b.id)),min(1.0 * (select count(e) from Enrolment e where e.brotherhood.id = b.id)),max(1.0 * (select count(e) from Enrolment e where e.brotherhood.id = b.id)),stddev(1.0 * (select count(e) from Enrolment e where e.brotherhood.id = b.id)) from Brotherhood b")
	double queryC1();

	//C2 - The largest brotherhood, minimum 1
	@Query("select e.brotherhood.id,e.brotherhood.title, count(e) from Enrolment e group by e.brotherhood order by 1 desc")
	Object[][] queryC2();
	//C3 - The smallest brotherhood, minimum 1
	@Query("select e.brotherhood.id,e.brotherhood.title, count(e) from Enrolment e group by e.brotherhood order by 1")
	Object[][] queryC3();
	//C4 - The ratio of requests to march in a procession, grouped by their status.
	@Query("select a.status,(count(a)*1.0)/(select count(aa) from Request aa) from Request a group by a.status")
	Object[][] queryC4();
	//C5 - The processions that are going to be organised in 30 days or less.
	@Query("select p from Procession p where p.moment BETWEEN current_timestamp and :currentDayPlus30Days;")
	Collection<Procession> queryC5(@Param("currentDayPlus30Days") Date date);
	//C6 - The ratio of requests to march grouped by status.
	// Duplicate
	//C7 - The listing of members who have got at least 10% the maximum number request to march accepted. 
	@Query("select m from Member m where 0.1<=(select count(q)*1/(select count(qq) from Request qq)	from Request q where q.status = 'APPROVED' and q.member.id=m.id)")
	Collection<Member> queryC7();
	//C8 - A histogram of positions.
	@Query("select e.positionEN,e.positionES, count(e) from Enrolment e group by e.positionEN")
	Object[][] queryC8();
	//DASHBOARD B
	//B1-A
	@Query("select b.area.name,count(b)*1.0/(select count(a) from Area a),count(b) from Brotherhood b group by b.area order by 2 desc")
	ResultSet queryB1A();
	//B1-B
	//	@Query("select b.area.name,count(b)*1.0/(select count(a) from Area a),count(b) from Brotherhood b group by b.area order by 2 desc")
	//	ResultSet queryB1B();
	//	//B2
	//	@Query("")
	//	//B3
	//	@Query("")

}
