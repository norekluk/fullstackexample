package org.example.springbeexample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class HttpResponseTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    public void rootResponseTest() {
        assertEquals("Hello World from Spring Boot",
                testRestTemplate.getForObject("http://localhost:" + port + "/", String.class));
    }

    @Test
    public void goodbyeResponseTest() {
        assertEquals("Goodbye from SpringBoot",
                testRestTemplate.getForObject("http://localhost:" + port + "/goodbye", String.class));
    }


    @Autowired
    private MockMvc mvc;

    @Test
    public void rootResponseTestWithMockMvc() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World from Spring Boot")));
    }
}
