package org.harvanir.demo.testcontainer.entrypoint.controller;

import lombok.extern.slf4j.Slf4j;
import org.harvanir.demo.testcontainer.TestContainerApplication;
import org.harvanir.demo.testcontainer.jpa.entity.Item;
import org.harvanir.demo.testcontainer.jpa.repository.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.harvanir.demo.testcontainer.entrypoint.controller.ControllerUtils.getExpectedDate;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Harvan Irsyadi
 */
@Slf4j
@AutoConfigureMockMvc
@SpringBootTest(classes = TestContainerApplication.class)
class ItemControllerIT {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MockMvc mockMvc;

    private Item item;

    @AfterEach
    void afterEach() {
        Optional.ofNullable(item).ifPresent(this::delete);
    }

    private void delete(Item obj) {
        itemRepository.delete(obj);
    }

    private Item createItem() {
        Item newItem = new Item();
        newItem.setName("Name");
        newItem.setPrice(BigDecimal.TEN);
        newItem.setQuantity(10);

        return itemRepository.save(newItem);
    }

    @Test
    void givenPredefinedData_WhenFindAll_ThenFoundTheData() throws Exception {
        item = createItem();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(ApiConstant.ITEM)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", equalTo(item.getId())))
                .andExpect(jsonPath("$[0].name", equalTo(item.getName())))
                .andExpect(jsonPath("$[0].quantity", equalTo(item.getQuantity())))
                .andExpect(jsonPath("$[0].price", equalTo(item.getPrice().doubleValue())))
                .andExpect(jsonPath("$[0].createdAt", equalTo(getExpectedDate(item.getCreatedAt()))))
                .andExpect(jsonPath("$[0].updatedAt", equalTo(getExpectedDate(item.getUpdatedAt()))))
                .andReturn();

        Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, mvcResult.getResponse().getContentType());
    }

    @Test
    void givenPredefinedData_WhenFindById_ThenFoundTheData() throws Exception {
        item = createItem();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(ApiConstant.ITEM + ApiConstant.ID, item.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(item.getId())))
                .andExpect(jsonPath("$.name", equalTo(item.getName())))
                .andExpect(jsonPath("$.quantity", equalTo(item.getQuantity())))
                .andExpect(jsonPath("$.price", equalTo(item.getPrice().doubleValue())))
                .andExpect(jsonPath("$.createdAt", equalTo(getExpectedDate(item.getCreatedAt()))))
                .andExpect(jsonPath("$.updatedAt", equalTo(getExpectedDate(item.getUpdatedAt()))))
                .andReturn();

        Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, mvcResult.getResponse().getContentType());
    }
}