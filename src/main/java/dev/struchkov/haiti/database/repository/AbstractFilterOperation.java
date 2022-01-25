package dev.struchkov.haiti.database.repository;

import dev.struchkov.haiti.context.page.Pagination;
import dev.struchkov.haiti.context.page.Sheet;
import dev.struchkov.haiti.filter.Filter;
import dev.struchkov.haiti.filter.FilterOperation;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;

import static dev.struchkov.haiti.utils.Assert.isNotNull;

public abstract class AbstractFilterOperation<T, K> implements FilterOperation<T> {

    private final JpaRepositoryImplementation<T, K> jpaRepository;

    protected AbstractFilterOperation(JpaRepositoryImplementation<T, K> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Sheet<T> findAll(Filter filter, Pagination pagination) {
        isNotNull(filter, pagination);
        return OperationJpa.findAll(jpaRepository, filter.build(), pagination);
    }

    @Override
    public Optional<T> findFirst(Filter filter) {
        isNotNull(filter);
        return OperationJpa.findFirst(jpaRepository, filter.build());
    }

    @Override
    public boolean exists(Filter filter) {
        isNotNull(filter);
        return OperationJpa.exists(jpaRepository, filter.build());
    }

    @Override
    public long count(Filter filter) {
        isNotNull(filter);
        return OperationJpa.count(jpaRepository, filter.build());
    }

}
