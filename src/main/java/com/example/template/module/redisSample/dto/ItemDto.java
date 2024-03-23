package com.example.template.module.redisSample.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDto implements Serializable {
    private String name;
    private int price;
    private int quantity;
}