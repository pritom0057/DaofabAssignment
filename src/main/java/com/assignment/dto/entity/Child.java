package com.assignment.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Child {

    private Integer id;

    private Integer parentId;

    private Integer paidAmount;
}
