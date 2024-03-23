package com.example.template.module.redisSample.controller;

import com.example.template.module.redisSample.dao.CartDao;
import com.example.template.module.redisSample.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CartController {
	private final CartDao cartDao;


	@PostMapping("/{id}/cart")
	public String addItemToCart(@PathVariable Long id, @RequestBody ItemDto itemDto){
		cartDao.addItem(itemDto, id);
		log.info("customer {} - add item to cart  : {} ", id, itemDto);
		return "success";
	}

	@GetMapping("/{id}/cart")
	public ItemDto getCartById(@PathVariable Long id){
		log.info("customer {} - find item from cart  ", id);
		return cartDao.findById(id);
	}

}