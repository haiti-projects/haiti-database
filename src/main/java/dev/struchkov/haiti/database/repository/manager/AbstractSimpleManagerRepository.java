package dev.struchkov.haiti.database.repository.manager;

import dev.struchkov.haiti.context.domain.BasicEntity;
import dev.struchkov.haiti.context.page.Pagination;
import dev.struchkov.haiti.context.page.Sheet;
import dev.struchkov.haiti.context.repository.SimpleManagerRepository;
import dev.struchkov.haiti.database.repository.OperationJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static dev.struchkov.haiti.utils.Assert.isNotNull;

public abstract class AbstractSimpleManagerRepository<Entity extends BasicEntity<Key>, Key> implements SimpleManagerRepository<Entity, Key> {

    private final JpaRepository<Entity, Key> jpaRepository;

    protected AbstractSimpleManagerRepository(JpaRepository<Entity, Key> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Entity save(Entity entity) {
        isNotNull(entity);
        return OperationJpa.save(jpaRepository, entity);
    }

    @Override
    public Optional<Entity> findById(Key id) {
        isNotNull(id);
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

    @Override
    public List<Entity> saveAll(Collection<Entity> entities) {
        isNotNull(entities);
        return OperationJpa.saveAll(jpaRepository, entities);
    }

    @Override
    public void deleteAllById(Collection<Key> ids) {
        isNotNull(ids);
        OperationJpa.deleteAllById(jpaRepository, ids);
    }

    @Override
    public Sheet<Entity> findAll(Pagination pagination) {
        isNotNull(pagination);
        return OperationJpa.findAll(jpaRepository, pagination);
    }

    @Override
    public List<Entity> findAllById(Collection<Key> collection) {
        isNotNull(collection);
        return OperationJpa.findAllById(jpaRepository, collection);
    }

}
