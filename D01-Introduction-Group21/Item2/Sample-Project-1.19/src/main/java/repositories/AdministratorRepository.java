
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

	@Query("select h from Administrator h where h.userAccount.id = ?1")
	Administrator findAdministratorByUserAccount(int userAccountId);

	@Query("select a from Actor a where a.userAccount.username=?1")
	Administrator findAdminByUsername(String username);

}
