package com.chatop.chatopapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.chatop.chatopapi.controller.RentalController;
import com.chatop.chatopapi.service.RentalService;

@WebMvcTest(controllers = RentalController.class)
class RentalControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RentalService rentalService;

	@Test
	void contextLoads() {
	}

	@Test
	void testGetRentals() throws Exception {
		mockMvc.perform(get("/api/rentals")).andExpect(status().isOk());
	}

}
