package com.example.spring.pagination.service.specification;

import com.example.spring.pagination.dao.entity.UserEntity;
import com.example.spring.pagination.model.criteria.UserCriteria;
import com.example.spring.pagination.util.PredicateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static com.example.spring.pagination.dao.entity.UserEntity.Fields.age;
import static com.example.spring.pagination.dao.entity.UserEntity.Fields.birthplace;
import static com.example.spring.pagination.util.PredicateUtil.applyLikePattern;

@AllArgsConstructor(staticName = "of")
public class UserSpecification implements Specification<UserEntity> {
    private UserCriteria userCriteria;

    @Override
    public Predicate toPredicate(Root<UserEntity> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder cb) {
        var predicates = PredicateUtil.builder()
                .addNullSafety(userCriteria.getBirthplace(),
                        birthPlace -> cb.like(root.get(birthplace), applyLikePattern(birthPlace)))
                .addNullSafety(userCriteria.getAgeFrom(),
                        ageFrom -> cb.greaterThanOrEqualTo(root.get(age), ageFrom))
                .addNullSafety(userCriteria.getAgeTo(),
                        ageTo -> cb.lessThanOrEqualTo(root.get(age), ageTo))
                .build();

        return cb.and(predicates);
    }
}
