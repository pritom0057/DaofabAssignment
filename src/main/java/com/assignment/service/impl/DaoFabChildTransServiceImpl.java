package com.assignment.service.impl;

import com.assignment.dto.entity.Child;
import com.assignment.dto.entity.Parent;
import com.assignment.dto.response.ChildDetailData;
import com.assignment.repository.IDaoFabTransDataAccessService;
import com.assignment.repository.IDaoFabTransInstallAccessService;
import com.assignment.service.IDaoFabChildTransService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class DaoFabChildTransServiceImpl implements IDaoFabChildTransService {

    @Autowired
    IDaoFabTransDataAccessService daoFabTransDataAccessService;

    @Autowired
    IDaoFabTransInstallAccessService daoFabTransInstallAccessService;

    @Override
    @SneakyThrows
    public List<ChildDetailData> fetchChildTransDataByParentId(Integer parentId) {

        var parentFuture =
                CompletableFuture.supplyAsync(() -> daoFabTransDataAccessService.fetchParentDataById(parentId));

        var childFurures =
                CompletableFuture.supplyAsync(() -> daoFabTransInstallAccessService.fetchTransInstallmentByParentId(parentId));

        var parentTrans = parentFuture.get()
                                      .orElse(null);

        var childList = childFurures.get()
                                    .orElse(new ArrayList<Child>());

        if (parentTrans == null || childList.isEmpty()) {
            return new ArrayList<ChildDetailData>();
        }

        return getChildDetailDataResponse(childList, parentTrans);
    }

    private List<ChildDetailData> getChildDetailDataResponse(List<Child> childList, Parent parentTrans) {

        var childDetailData = new ArrayList<ChildDetailData>();

        childList.forEach(item -> {
            childDetailData.add(ChildDetailData.builder()
                                               .id(item.getId())
                                               .receiver(parentTrans.getReceiver())
                                               .sender(parentTrans.getSender())
                                               .totalAmount(parentTrans.getTotalAmount())
                                               .paidAmount(item.getPaidAmount())
                                               .build());
        });

        return childDetailData.stream()
                              .sorted(Comparator.comparing(ChildDetailData::getId))
                              .collect(Collectors.toList());
    }
}
