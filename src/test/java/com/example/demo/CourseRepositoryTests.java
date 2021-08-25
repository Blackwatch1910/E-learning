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
public class CourseRepositoryTests {

	@Autowired
	private CourseRepository c_repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateCourse() {
		Course course = new Course();
		course.setName("CS50");
		course.setDescription("This is Introduction to Computer Science");
		course.setFees("8999");
		course.setResource("edx.org");
		
		Course savedCourse = c_repo.save(course);
		
		Course existCourse = entityManager.find(Course.class, savedCourse.getId());
		
		assertThat(existCourse.getName().equals(course.getName()));
	}
}
