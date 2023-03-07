package com.assignment.service;


import com.assignment.dto.response.ParentAmount;

import java.util.List;

public interface IDaoFabService {

    List<ParentAmount> fetchAllParentAmount(int pageNo, int pageSize);
}
