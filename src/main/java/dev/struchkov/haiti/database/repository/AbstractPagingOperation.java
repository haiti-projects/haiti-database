package dev.struchkov.haiti.database.repository;

import dev.struchkov.haiti.context.page.Pagination;
import dev.struchkov.haiti.context.page.Sheet;
import dev.struchkov.haiti.context.repository.simple.PagingOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import static dev.struchkov.haiti.utils.Assert.isNotNull;

public abstract class AbstractPagingOperation<Entity, Key> implements PagingOperation<Entity> {

    private final JpaRepository<Entity, Key> jpaRepository;

    protected AbstractPagingOperation(JpaRepository<Entity, Key> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Sheet<Entity> findAll(Pagination pagination) {
        isNotNull(pagination);
        return OperationJpa.findAll(jpaRepository, pagination);
    }

}
