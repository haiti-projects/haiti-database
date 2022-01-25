package dev.struchkov.haiti.database.repository;

import dev.struchkov.haiti.context.domain.BasicEntity;
import dev.struchkov.haiti.context.repository.simple.MultipleOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

import static dev.struchkov.haiti.utils.Assert.isNotNull;

public abstract class AbstractMultipleOperation<T extends BasicEntity<K>, K> implements MultipleOperation<T, K> {

    protected final JpaRepository<T, K> jpaRepository;

    protected AbstractMultipleOperation(JpaRepository<T, K> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<T> saveAll(Collection<T> entities) {
        isNotNull(entities);
        return OperationJpa.saveAll(jpaRepository, entities);
    }

    @Override
    public void deleteAllById(Collection<K> ids) {
        isNotNull(ids);
        OperationJpa.deleteAllById(jpaRepository, ids);
    }

    @Override
    public List<T> findAllById(Collection<K> ids) {
        isNotNull(ids);
        return OperationJpa.findAllById(jpaRepository, ids);
    }

}
