package com.online.office;

import com.google.gson.Gson;
import com.online.office.controller.UserController;
import com.online.office.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(
        locations="classpath:application-integrationtest.properties")
public class OfficeApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserController userController;

    @Autowired
    private MockMvc mvc;

    @Test
    public void contextLoads() throws Exception {
        assertThat(userController).isNotNull();
    }

    @Test
    public void UserController_login() throws Exception {

        User user = new User();
        user.setName("username");
        user.setPassword("123456");
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        mvc.perform(
                MockMvcRequestBuilders.post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

    }

    @Test
    public void UserController_getUser() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.post("/user/getUser")
        ).andExpect(status().isOk());
    }

    @Test
    public void UserController_hello() throws Exception {
       /*
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/user/login",
                String.class)).contains("Hello, world");
        */


        mvc.perform(MockMvcRequestBuilders.get(
                "/user/hello")).andExpect(status().isOk());
    }
}
