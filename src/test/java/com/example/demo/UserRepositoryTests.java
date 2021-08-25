package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setDate("2021-10-02");
		user.setEmail("tiger@gmail.com");
		user.setName("Tiger");
		user.setPhone(12345678L);
		user.setPassword("xyz@123");
		user.setAddress("Paris");
		user.setPhoto("img1.jpg");
		
		User savedUser = repo.save(user);
		
		User existUser = entityManager.find(User.class, savedUser.getId());
		
		assertThat(existUser.getEmail().equals(user.getEmail()));
	}
	
	public void testFindUserByEmail() {
		String email = "blackwatch@hotmail.com";
		
		User user = repo.findByEmail(email);
		
		assertThat(user).isNotNull();
	}

}
