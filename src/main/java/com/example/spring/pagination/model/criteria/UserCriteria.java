package com.example.spring.pagination.model.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCriteria {
    private Integer ageFrom;
    private Integer ageTo;
    private String birthplace;
}
