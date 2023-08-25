package br.com.neo.user.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.Serializable;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.neo.token.dto.UserDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    
	@Autowired
	private MockMvc mockMvc;
	

	@Test
	public void testUser() throws JsonProcessingException, Exception {
		 this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/save/redis/user")
				    .content(asJsonString(new UserDTO("Teste",10)))
				    .contentType(MediaType.APPLICATION_JSON)
				    .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists());

	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	



}
