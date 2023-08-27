package br.com.neo.user.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.io.Serializable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.neo.token.controller.UserController;
import br.com.neo.token.dto.UserDTO;
import br.com.neo.token.exception.HandleExceptionRequest;
import br.com.neo.token.usecase.UserRedisUserCase;


@WebAppConfiguration
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest implements Serializable {
    
	private static final long serialVersionUID = -1384255112019995945L;

	private MockMvc mockMvc;
	
	@Mock
	private UserRedisUserCase service;
   
	@InjectMocks
	private UserController controller;
	

	
	@BeforeEach
	public void init() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new UserController())
				.setControllerAdvice(new HandleExceptionRequest()).build();
	}

	@Test
	public void userSalva() throws Exception {
		this.mockMvc.perform(post("/api/v1/save/redis/user").contentType(MediaType.APPLICATION_JSON).content(asJsonString(new UserDTO("Teste",10))))
				.andExpect(status().isInternalServerError());
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	



}
