package com.assignment.repository;

import com.assignment.dto.entity.Parent;
import com.assignment.utils.Constants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DaoFabDataAccessServiceImpl implements IDaoFabTransDataAccessService {

    @Value("${parent.trans.path:Parent.json}")
    String parentJsonFilePath;

    private List<Parent> parentList;

    @Override
    public Optional<List<Parent>> fetchAllParentData(int pageNo, int pageSize) {
        var skipNumber = pageNo * pageSize;
        return Optional.of(this.parentList.stream()
                                          .skip(skipNumber)
                                          .limit(pageSize)
                                          .collect(Collectors.toList()));
    }

    @Override
    public Optional<Parent> fetchParentDataById(Integer parentId) {

        return this.parentList.stream()
                              .filter(item -> item.getId() == parentId)
                              .findFirst();
    }

    @PostConstruct
    @SneakyThrows
    public void loadAllParentChildData() {
        var classloader = Thread.currentThread().getContextClassLoader();
        var inputStream = classloader.getResourceAsStream(parentJsonFilePath);
        var objectMapper = new ObjectMapper();

        var jsonMap = objectMapper.readValue(inputStream, new TypeReference<Map<String, Object>>() {
        });
        if (jsonMap != null && jsonMap.containsKey(Constants.DATA)) {
            //            this.parentList = (ArrayList<Parent>) jsonMap.get(Constants.DATA);
            this.parentList = objectMapper.convertValue(jsonMap.get(Constants.DATA), new TypeReference<List<Parent>>() {
            });
            //Default sorting by id
            this.parentList = this.parentList.stream().sorted(Comparator.comparing(Parent::getId))
                                             .collect(Collectors.toList());
        }

        if (this.parentList == null || this.parentList.isEmpty()) {
            throw new Exception("Unable to load the Parent JSON file...");
        }
        System.out.print("Successfully loaded parent JSON file...");
    }
}
