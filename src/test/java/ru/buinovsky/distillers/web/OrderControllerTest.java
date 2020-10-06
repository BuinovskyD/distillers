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
import ru.buinovsky.distillers.model.Order;
import ru.buinovsky.distillers.service.OrderService;

import javax.annotation.PostConstruct;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static ru.buinovsky.distillers.OrderTestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.hamcrest.Matchers.*;
import static ru.buinovsky.distillers.UserTestData.MANAGER_1;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-mvc.xml"
})
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
class OrderControllerTest {

    public MockMvc mockMvc;

    @Autowired
    private OrderService service;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @PostConstruct
    private void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(get("/orders/delete/{id}", ORDER_ID))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/orders"));

        Assertions.assertNull(service.get(ORDER_ID, MANAGER_1_ID));
    }

    @Test
    void createOrderTest() throws Exception {
        Order inputOrder = getNewOrder();

        mockMvc.perform(post("/orders/edit")
                .sessionAttr("inputOrder", inputOrder))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/orders"))
                .andExpect(model().attribute("order", hasProperty("customerName", is(inputOrder.getCustomerName()))));
    }

    @Test
    void updateOrderTest() throws Exception {
        Order inputOrder = getUpdatedOrder();

        mockMvc.perform(post("/orders/edit")
                .sessionAttr("inputOrder", inputOrder))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/orders"))
                .andExpect(model().attribute("order", hasProperty("customerName", is(inputOrder.getCustomerName()))));
    }

    @Test
    void getAllByUserTest() throws Exception {
        mockMvc.perform(get("/orders"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("orders"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/orders.jsp"))
                .andExpect(model().attribute("orders", hasSize(ORDERS.size())));
    }

    @Test
    void getAllFilteredByUserTest() throws Exception {
        mockMvc.perform(get("/orders/filter")
                .param("startDate", START_DATE.toString())
                .param("endDate", END_DATE.toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("orders"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/orders.jsp"))
                .andExpect(model().attribute("orders", hasSize(ORDERS_FILTERED.size())));
    }
}