package dev.struchkov.haiti.database.repository;

import dev.struchkov.haiti.context.repository.simple.CrudOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import static dev.struchkov.haiti.utils.Assert.isNotNull;

public abstract class AbstractCrudOperation<Entity, Key> implements CrudOperation<Entity, Key> {

    private final JpaRepository<Entity, Key> jpaRepository;

    protected AbstractCrudOperation(JpaRepository<Entity, Key> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Entity save(Entity entity) {
        return OperationJpa.save(jpaRepository, entity);
    }

    @Override
    public Optional<Entity> findById(Key id) {
        return OperationJpa.findById(jpaRepository, id);
    }

    @Override
    public boolean existsById(Key id) {
        isNotNull(id);
        return OperationJpa.existsById(jpaRepository, id);
    }

    @Override
    public void deleteById(Key id) {
        isNotNull(id);
        OperationJpa.deleteById(jpaRepository, id);
    }

}
