package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private CourseRepository c_repo;
	
	@GetMapping("")
	public String viewHomepage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processedRegistration(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		repo.save(user);
		
		return "register_success";
	}
	
	@GetMapping("/list_users")
	public String viewUsersList(Model model) {
		
		List<User> listUsers = repo.findAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
	}
	
	@GetMapping("/register_course")
	public String showCourseRegistrationForm(Model model) {
		model.addAttribute("course", new Course());
		
		return "course_register";
	}
	
	@PostMapping("/course_registered")
	public String courseRegistrationHandler(Course course) {
		
		c_repo.save(course);
		
		return "course_reg_success";
	}
	
	
	@GetMapping("/view_courses")
	public String viewCourseList(Model model) {
		List<Course> listCourses = c_repo.findAll();
		model.addAttribute("listCourses", listCourses);
		return "courses";
	}

}
