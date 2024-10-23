package com.example.cat_project.Core.Controller;

import org.springframework.data.domain.Sort;

public class ApiController {

    protected Sort getSort(String order) {
        return Sort.by(order.startsWith("-") ? Sort.Direction.DESC : Sort.Direction.ASC,
                order.startsWith("-") ? order.substring(1) : order);
    }

}
