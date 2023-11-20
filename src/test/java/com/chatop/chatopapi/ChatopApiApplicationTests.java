package com.chatop.chatopapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.chatop.chatopapi.controller.ApiController;
import com.chatop.chatopapi.service.ApiService;




@WebMvcTest(controllers = ApiController.class)
class RentalControllerTests {

	@Autowired
	private MockMvc mockMvc; 

	@MockBean
	private ApiService restService;

	@Test
	void contextLoads() {
	}

	@Test
	void testGetRentals() throws Exception {
		mockMvc.perform(get("/api/rentals")).andExpect(status().isOk());
	}
	
//	@Test
//	void testGetRental() throws Exception {
//		mockMvc.perform(get("/api/rentals/{id}",1))
//		.andExpect(status().isOk());
//	}
	
	@Test
	public void testAllEmployees() throws Exception 
	{
		mockMvc.perform(MockMvcRequestBuilders
	  			.get("/api/rentals")
	  			.accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk());
	      //.andExpect(MockMvcResultMatchers.jsonPath("$.rentals").exists());
	      //.andExpect(MockMvcResultMatchers.jsonPath("$.rentals[*].Id").isNotEmpty());
	}
	
//	@Test
//	public void testPutEmployees() throws Exception 
//	{
//		mockMvc.perform(MockMvcRequestBuilders
//	  			.put("/api/rentals")
//	  			.accept(MediaType.APPLICATION_JSON))
//	      .andExpect(status().isOk());
//	}
	

}
