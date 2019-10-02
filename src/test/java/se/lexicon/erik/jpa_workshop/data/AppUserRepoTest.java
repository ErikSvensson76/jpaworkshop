package se.lexicon.erik.jpa_workshop.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import se.lexicon.erik.jpa_workshop.entity.AppUser;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class AppUserRepoTest {
	
	@Autowired private AppUserRepo testObject;	
	
	@Test
	public void given_email_findByEmail_return_Optional_present() {
		AppUser user = testObject.save(new AppUser("Test", "Testsson", "test@test.com"));		
		AppUser expectedContent = new AppUser(user.getUserId(), "Test", "Testsson", "test@test.com");
		
		Optional<AppUser> result = testObject.findByEmail("test@test.com");
		
		assertTrue(result.isPresent());
		assertEquals(expectedContent, result.get());		
	}

}
