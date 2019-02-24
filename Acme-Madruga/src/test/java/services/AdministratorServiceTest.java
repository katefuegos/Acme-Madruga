
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AdministratorServiceTest extends AbstractTest {

	//Service----------------------------------------------------------
	@Autowired
	private AdministratorService	administratorService;


	//Test-------------------------------------------------------------

	@Test
	public void testDashBoardQueryC2() {
		System.out.println("========== testDashBoardQueryC2() ==========");

		try {

			final Collection<Object[]> result = this.administratorService.queryC2();
			Assert.notNull(result);
			System.out.println(result);

			for (final Object[] objects : result) {
				for (int i = 0; i < objects.length; i++)
					System.out.println(String.valueOf(objects[i]));
				System.out.println("--------------");

			}
			System.out.println("¡Exito!");

		} catch (final Exception e) {
			System.out.println("¡Fallo," + e.getMessage() + "!");
		}
	}

}
