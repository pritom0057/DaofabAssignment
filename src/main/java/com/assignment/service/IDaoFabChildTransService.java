package com.assignment.service;

import com.assignment.dto.response.ChildDetailData;

import java.util.List;

public interface IDaoFabChildTransService {

    List<ChildDetailData> fetchChildTransDataByParentId(Integer parentId);
}
