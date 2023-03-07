package com.assignment.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Parent {

    private Integer id;

    private String sender;

    private String receiver;

    private Integer totalAmount;
}
