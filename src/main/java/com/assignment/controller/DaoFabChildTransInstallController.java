package com.assignment.controller;

import com.assignment.dto.response.ChildDetailData;
import com.assignment.service.IDaoFabChildTransService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/internal/v1/daofab-child-transaction")
@Slf4j
public class DaoFabChildTransInstallController {

    @Autowired
    IDaoFabChildTransService daoFabChildTransService;

    @GetMapping("/data")
    public ResponseEntity<List<ChildDetailData>> fetchChildTransByParentId(@RequestParam(name = "parent_id", required = true) Integer parentId) {
        var childDetailDataList = daoFabChildTransService.fetchChildTransDataByParentId(parentId);
        return Optional.ofNullable(childDetailDataList)
                       .map(ResponseEntity::ok)
                       .orElseGet(() -> new ResponseEntity(HttpStatus.NO_CONTENT));
    }
}
