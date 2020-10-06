package ru.buinovsky.distillers.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import ru.buinovsky.distillers.model.Order;
import ru.buinovsky.distillers.service.OrderService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static ru.buinovsky.distillers.OrderTestData.*;
import static ru.buinovsky.distillers.UserTestData.MANAGER_1;

class OrderRestControllerTest extends AbstractControllerTest {

    @Autowired
    private OrderService service;

    @Test
    void getAllTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/rest/orders")
                .with(httpBasic(MANAGER_1.getEmail(), "password")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        JSONAssert.assertEquals(getJsonAllOrders(ORDERS), content, false);
    }

    @Test
    void getTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/rest/orders/{id}", ORDER_ID)
                .with(httpBasic(MANAGER_1.getEmail(), "password")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        JSONAssert.assertEquals(getJsonOrder(ORDER_3), content, false);
    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(delete("/rest/orders/{id}", ORDER_ID)
                .with(httpBasic(MANAGER_1.getEmail(), "password")))
                .andDo(print())
                .andExpect(status().isNoContent());

        Assertions.assertNull(service.get(ORDER_ID, MANAGER_1_ID));
    }

    @Test
    void updateTest() throws Exception {
        String inputOrder = getJsonOrder(getUpdatedOrder());

        mockMvc.perform(put("/rest/orders/{id}", ORDER_ID)
                .with(httpBasic(MANAGER_1.getEmail(), "password"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputOrder))
                .andExpect(status().isNoContent())
                .andDo(print());

        Order order = service.get(ORDER_ID, MANAGER_1_ID);
        Assertions.assertEquals(UPDATED_ORDER, order);
    }

    @Test
    void createTest() throws Exception {
        String inputOrder = getJsonOrder(getNewOrder());

        MvcResult mvcResult = mockMvc.perform(post("/rest/orders")
                .with(httpBasic(MANAGER_1.getEmail(), "password"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputOrder))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        JSONAssert.assertEquals(inputOrder, content, false);
    }

    @Test
    void getAllFilteredTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/rest/orders/filter")
                .with(httpBasic(MANAGER_1.getEmail(), "password"))
                .param("startDate", START_DATE.toString())
                .param("endDate", END_DATE.toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        JSONAssert.assertEquals(getJsonAllOrders(ORDERS_FILTERED), content, false);
    }
}