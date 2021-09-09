package com.online.office.controller;

import com.google.gson.Gson;
import com.online.office.po.User;
import com.online.office.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    /**
     * 不能执行单元测试：因为，容器中没有创建SecurityManager Bean，除非自行配置创建SecurityMananger
     *  Error message:
     *  org.springframework.web.util.NestedServletException: Request processing failed; nested exception is
     *  org.apache.shiro.UnavailableSecurityManagerException: No SecurityManager accessible to the calling code,
     *  either bound to the org.apache.shiro.util.ThreadContext or as a vm static singleton.
     *  This is an invalid application configuration.
     */
    @Test
    public void TestLogin() throws Exception {
        User user = new User();
        user.setName("username");
        user.setPassword("1234567");
        Gson gson = new Gson();
        String userJson = gson.toJson(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void TestGetUser() throws Exception {
/*        Mockito.when(userService.getUser()).thenReturn("hello");

        mockMvc.perform(MockMvcRequestBuilders.get("/user/getUser"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("hello"));*/
    }

}