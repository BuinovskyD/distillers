package ru.buinovsky.distillers.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import ru.buinovsky.distillers.model.User;
import ru.buinovsky.distillers.service.UserService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static ru.buinovsky.distillers.UserTestData.*;

class UserRestControllerTest extends AbstractControllerTest {

    @Autowired
    private UserService service;

    @Test
    void getAllTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/rest/admin/users")
                .with(httpBasic(ADMIN.getEmail(), "admin")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        JSONAssert.assertEquals(getJsonAllUsers(), content, false);
    }

    @Test
    void getTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/rest/admin/users/{id}", MANAGER_ID)
                .with(httpBasic(ADMIN.getEmail(), "admin")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        JSONAssert.assertEquals(getJsonUser(MANAGER_1), content, false);
    }

    @Test
    void createTest() throws Exception {
        String inputUser = getJsonUser(getNewUser());

        MvcResult mvcResult = mockMvc.perform(post("/rest/admin/users")
                .with(httpBasic(ADMIN.getEmail(), "admin"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputUser))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        JSONAssert.assertEquals(getJsonIgnoreProperties(inputUser, "password"),
                getJsonIgnoreProperties(content, "password"), false);
    }

    @Test
    void updateTest() throws Exception {
        String inputUser = getJsonUser(getUpdatedUser());

        mockMvc.perform(put("/rest/admin/users/{id}", MANAGER_ID)
                .with(httpBasic(ADMIN.getEmail(), "admin"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputUser))
                .andExpect(status().isNoContent())
                .andDo(print());

        User updated = service.get(MANAGER_ID);
        Assertions.assertEquals(UPDATED_MANAGER, updated);
    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(delete("/rest/admin/users/{id}", MANAGER_ID)
                .with(httpBasic(ADMIN.getEmail(), "admin")))
                .andDo(print())
                .andExpect(status().isNoContent());

        Assertions.assertNull(service.get(MANAGER_ID));
    }

    @Test
    void getAllNotForbidden() throws Exception {
        mockMvc.perform(get("/rest/admin/users")
                .with(httpBasic(MANAGER_1.getEmail(), "password")))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
}