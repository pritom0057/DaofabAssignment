package com.assignment.repository;


import com.assignment.dto.entity.Parent;

import java.util.List;
import java.util.Optional;

public interface IDaoFabTransDataAccessService {

    Optional<List<Parent>> fetchAllParentData(int pageNo, int pageSize);

    Optional<Parent> fetchParentDataById(Integer parentId);
}
