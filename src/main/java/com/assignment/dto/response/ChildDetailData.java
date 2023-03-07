package com.assignment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChildDetailData {

    private Integer id;

    private Integer paidAmount;

    private String sender;

    private String receiver;

    private Integer totalAmount;
}
