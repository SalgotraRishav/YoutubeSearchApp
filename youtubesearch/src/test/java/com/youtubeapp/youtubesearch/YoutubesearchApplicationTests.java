package com.youtubeapp.youtubesearch;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "youtube.api.key=dummy-test-key")
class YoutubesearchApplicationTests {

	@Test
	void contextLoads() {
	}

}
