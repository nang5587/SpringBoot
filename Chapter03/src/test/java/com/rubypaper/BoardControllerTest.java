package com.rubypaper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.rubypaper.domain.BoardVO;
//import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class BoardControllerTest {
	
//	@Autowired
//	private MockMvc mockMvc;
//	
//	@Test
//	public void testHello() throws Exception {
//		mockMvc.perform(get("/hello").param("name", "둘리"))
//		.andExpect(status().isOk())
//		.andExpect(content().string("Hello : 둘리"))
//		.andDo(print());
//	}
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	@DisplayName("/helllo 테스트")
	public void testHello() throws Exception {
		String result = restTemplate.getForObject("/hello?name=둘리", String.class);
		
		assertEquals("Hello : 둘리", result);
	}
	
	@Test
	@DisplayName("/getBoard 테스트")
	public void testBoard() throws Exception {
		BoardVO board = restTemplate.getForObject("/getBoard", BoardVO.class);
		
		assertEquals("테스터", board.getWriter());
	}

}
