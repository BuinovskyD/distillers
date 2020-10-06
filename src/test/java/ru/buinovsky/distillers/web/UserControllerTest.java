package ru.buinovsky.distillers.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import ru.buinovsky.distillers.model.User;
import ru.buinovsky.distillers.service.UserService;

import javax.annotation.PostConstruct;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static ru.buinovsky.distillers.UserTestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.hamcrest.Matchers.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-mvc.xml"
})
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
public class UserControllerTest {

    public MockMvc mockMvc;

    @Autowired
    private UserService service;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @PostConstruct
    private void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
//                .apply(springSecurity())
                .build();
    }

    @Test
    public void getAllTest() throws Exception {
        mockMvc.perform(get("/users"))
//                .with(httpBasic(ADMIN.getEmail(), "admin")))
                .andDo(print())
//                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
                .andExpect(model().attribute("users", hasSize(USERS.size())));
    }

    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(get("/users/delete/{id}", MANAGER_ID))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/users"));

        Assertions.assertNull(service.get(MANAGER_ID));
    }

    @Test
    public void createTest() throws Exception {
        User inputUser = getNewUser();

        mockMvc.perform(post("/users/edit").sessionAttr("user", inputUser))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/users"))
                .andExpect(model().attribute("user", hasProperty("name", is(inputUser.getName()))));
    }

    @Test
    public void updateTest() throws Exception {
        User inputUser = getUpdatedUser();

        mockMvc.perform(post("/users/edit").sessionAttr("user", inputUser))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/users"));
    }
}