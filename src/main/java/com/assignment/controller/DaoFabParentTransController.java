package com.assignment.controller;

import com.assignment.dto.response.ParentAmount;
import com.assignment.service.IDaoFabService;
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
@RequestMapping("/api/internal/v1/daofab-parent-transaction")
@Slf4j
public class DaoFabParentTransController {

    @Autowired
    IDaoFabService daoFabService;

    @GetMapping("/data")
    public ResponseEntity<List<ParentAmount>> fetchAllParentAmount(@RequestParam(name = "page_no", required = true) Integer pageNo) {
        var parentAmounts = daoFabService.fetchAllParentAmount(pageNo, 2);
        return Optional.ofNullable(parentAmounts)
                       .map(ResponseEntity::ok)
                       .orElseGet(() -> new ResponseEntity(HttpStatus.NO_CONTENT));
    }
}
