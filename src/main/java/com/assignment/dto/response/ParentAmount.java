package com.assignment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParentAmount {

    private Integer id;

    private String sender;

    private String receiver;

    private Integer totalAmount;

    private Integer totalPaidAmount;
}
