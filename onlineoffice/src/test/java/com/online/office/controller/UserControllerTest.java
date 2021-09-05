package com.online.office.controller;

import com.online.office.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    /*
    We can use the @MockBean to add mock objects to the Spring application context.
    The mock will replace any existing bean of the same type in the application context.
    If no bean of the same type is defined, a new one will be added.
    This annotation is useful in integration tests where a particular bean,
    like an external service, needs to be mocked.
     */
    @MockBean
    private UserService userService;

    @Test
    public void TestLogin() throws Exception {
        /*
          Mock UserSerivce.getUser() method return "hello", because, "/user/getUser" path handle method :
          UserController.getUser(), will call UserService.getUser().
         */
        Mockito.when(userService.getUser()).thenReturn("hello");

        mockMvc.perform(MockMvcRequestBuilders.get("/user/getUser"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("hello"));
    }

}