package com.example.template.module.redisSample.controller;

import com.example.template.AbstractContainerBaseTest;
import com.example.template.IntegrationTest;
import com.example.template.module.redisSample.dao.CartDao;
import com.example.template.module.redisSample.dto.ItemDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@IntegrationTest
class CartControllerTest extends AbstractContainerBaseTest {

    @Autowired
    private CartDao cartDao;

    @Test
    void addCartItem() {
        ItemDto item = ItemDto.builder()
                .name("item")
                .price(1000)
                .quantity(2)
                .build();

        // when
        cartDao.addItem(item, 1L);

        ItemDto findItem = cartDao.findById(1L);
        assertEquals(findItem.getName(), "item");
        assertEquals(findItem.getPrice(), 1000);
        assertEquals(findItem.getQuantity(), 2);
    }

}