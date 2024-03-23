package com.example.template.module.redisSample.dao;

import com.example.template.module.redisSample.dto.ItemDto;
import com.example.template.module.redisSample.util.KeyGen;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class CartDao {

    private final RedisTemplate<String, Object> redisTemplate;

    public void addItem(ItemDto itemDto, Long customerId) {
        String key = KeyGen.cartKeyGenerate(customerId); // "cart:{customerId}"

        redisTemplate.opsForValue().set(key, itemDto);
        redisTemplate.expire(key, 1, TimeUnit.MINUTES);
    }

    public ItemDto findById(Long customerId) {
        String key = KeyGen.cartKeyGenerate(customerId); // "cart:{customerId}"

        return (ItemDto) redisTemplate.opsForValue().get(key);
    }
}