package com.tr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import com.tr.controllers.UserController;
import com.tr.entity.User;
import com.tr.repositories.UserRepository;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SocialMediaApplicationTests {
	
	@InjectMocks
	private UserController usercontroller;
	
	@MockBean
	private UserRepository repo;
	
	
	
	@Test
	public void getUsers() {
		when(repo.findAll()).thenReturn(Stream.of(new User(10, "Bharath", LocalDateTime.now())).collect(Collectors.toList()));
//		assertEquals(3, );
	}

}
