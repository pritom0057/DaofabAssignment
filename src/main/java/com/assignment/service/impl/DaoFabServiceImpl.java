package com.assignment.service.impl;

import com.assignment.dto.entity.Child;
import com.assignment.dto.entity.Parent;
import com.assignment.dto.response.ParentAmount;
import com.assignment.repository.IDaoFabTransDataAccessService;
import com.assignment.repository.IDaoFabTransInstallAccessService;
import com.assignment.service.IDaoFabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DaoFabServiceImpl implements IDaoFabService {

    @Autowired
    IDaoFabTransDataAccessService daoFabTransactionDataAccessService;

    @Autowired
    IDaoFabTransInstallAccessService daoFabTransInstallAccessService;

    @Override
    public List<ParentAmount> fetchAllParentAmount(int pageNo, int pageSize) {

        var parentList = daoFabTransactionDataAccessService.fetchAllParentData(pageNo, pageSize)
                                                           .orElse(new ArrayList<Parent>());

        var childList = daoFabTransInstallAccessService.fetchTransInstallByParentIds(parentList.stream()
                                                                                               .map(Parent::getId)
                                                                                               .collect(Collectors.toList()))
                                                       .orElse(new ArrayList<Child>());

        var parentTotalAmount = getParentTransPaidAmount(childList);

        return getPaidAmountResponse(parentList, parentTotalAmount);
    }

    private Map<Integer, Integer> getParentTransPaidAmount(List<Child> childList) {
        Map<Integer, Integer> parentTotalPaidAmoMap = new HashMap<Integer, Integer>();
        for (var child : childList) {
            parentTotalPaidAmoMap.put(child.getParentId(),
                    parentTotalPaidAmoMap.getOrDefault(child.getParentId(), 0) + child.getPaidAmount());
        }
        return parentTotalPaidAmoMap;
    }

    private List<ParentAmount> getPaidAmountResponse(List<Parent> parentList, Map<Integer, Integer> parentTotalPaidAmoMap) {
        List<ParentAmount> parentAmounts = new ArrayList<ParentAmount>();

        parentList.forEach(item -> {
            parentAmounts.add(ParentAmount.builder()
                                          .id(item.getId())
                                          .totalAmount(item.getTotalAmount())
                                          .totalPaidAmount(parentTotalPaidAmoMap.getOrDefault(item.getId(), 0))
                                          .receiver(item.getReceiver())
                                          .sender(item.getSender())
                                          .build()
            );
        });
        return parentAmounts;
    }
}
