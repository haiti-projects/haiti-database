package dev.struchkov.haiti.database.repository.manager;

import dev.struchkov.haiti.context.domain.BasicEntity;
import dev.struchkov.haiti.context.page.Pagination;
import dev.struchkov.haiti.context.page.Sheet;
import dev.struchkov.haiti.database.repository.OperationJpa;
import dev.struchkov.haiti.filter.Filter;
import dev.struchkov.haiti.filter.FilterOperation;
import org.slf4j.Logger;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;

import static dev.struchkov.haiti.utils.Assert.isNotNull;

public abstract class FilterManagerRepository<Entity extends BasicEntity<Key>, Key>
        extends AbstractSimpleManagerRepository<Entity, Key> implements FilterOperation<Entity> {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(FilterManagerRepository.class);

    private final JpaRepositoryImplementation<Entity, Key> jpaRepositoryImplementation;

    protected FilterManagerRepository(JpaRepositoryImplementation<Entity, Key> jpaRepository) {
        super(jpaRepository);
        this.jpaRepositoryImplementation = jpaRepository;
    }

    @Override
    public Sheet<Entity> findAll(Filter filter, Pagination pagination) {
        isNotNull(filter, pagination);
        return OperationJpa.findAll(jpaRepositoryImplementation, filter.build(), pagination);
    }

    @Override
    public Optional<Entity> findFirst(Filter filter) {
        isNotNull(filter);
        try {
            return OperationJpa.findFirst(jpaRepositoryImplementation, filter.build());
        } catch (InvalidDataAccessResourceUsageException e) {
            log.error(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean exists(Filter filter) {
        isNotNull(filter);
        try {
            return OperationJpa.exists(jpaRepositoryImplementation, filter.build());
        } catch (InvalidDataAccessResourceUsageException e) {
            log.error(e.getMessage());
        }
        return false;
    }

    @Override
    public long count(Filter filter) {
        isNotNull(filter);
        try {
            return OperationJpa.count(jpaRepositoryImplementation, filter.build());
        } catch (InvalidDataAccessResourceUsageException e) {
            log.error(e.getMessage());
        }
        return 0;
    }

}
