package dev.struchkov.haiti.database.repository;

import dev.struchkov.haiti.context.page.Pagination;
import dev.struchkov.haiti.context.page.Sheet;
import dev.struchkov.haiti.database.util.Converter;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static dev.struchkov.haiti.utils.Exceptions.utilityClass;

public class OperationJpa {

    private OperationJpa() {
        utilityClass();
    }

    public static <Entity, Key> Entity save(JpaRepository<Entity, Key> jpaRepository, Entity entity) {
        return jpaRepository.save(entity);
    }

    public static <Entity, Key> Optional<Entity> findById(JpaRepository<Entity, Key> jpaRepository, Key id) {
        return jpaRepository.findById(id);
    }

    public static <Entity, Key> boolean existsById(JpaRepository<Key, Entity> jpaRepository, Entity id) {
        return jpaRepository.existsById(id);
    }

    public static <Entity, Key> void deleteById(JpaRepository<Key, Entity> jpaRepository, Entity id) {
        jpaRepository.deleteById(id);
    }

    public static <Entity, Key> Sheet<Entity> findAll(JpaRepository<Entity, Key> jpaRepository, Pagination pagination) {
        return Converter.page(jpaRepository.findAll(Converter.pagination(pagination)));
    }

    public static <Entity, Key> List<Entity> findAllById(JpaRepository<Entity, Key> jpaRepository, Collection<Key> ids) {
        try {
            return jpaRepository.findAllById(ids);
        } catch (InvalidDataAccessResourceUsageException exception) {
            return Collections.emptyList();
        }
    }

    public static <Entity, Key> List<Entity> saveAll(JpaRepository<Entity, Key> jpaRepository, Collection<Entity> entities) {
        return jpaRepository.saveAll(entities);
    }

    public static <Entity, Key> void deleteAllById(JpaRepository<Key, Entity> jpaRepository, Collection<Entity> ids) {
        ids.forEach(jpaRepository::deleteById);
    }

    public static <Entity, Key> Optional<Entity> findFirst(JpaRepositoryImplementation<Entity, Key> jpaRepository, Specification<Entity> specification) {
        return jpaRepository.findOne(specification);
    }

    public static <Entity, Key> boolean exists(JpaRepositoryImplementation<Key, Entity> jpaRepository, Specification<Key> specification) {
        return jpaRepository.count(specification) > 0;
    }

    public static <Entity, Key> List<Entity> findAll(JpaRepositoryImplementation<Entity, Key> jpaRepository, Specification<Entity> specification) {
        return jpaRepository.findAll(specification);
    }

    public static <Entity, Key> Sheet<Entity> findAll(JpaRepositoryImplementation<Entity, Key> jpaRepository, Specification<Entity> specification, Pagination pagination) {
        return Converter.page(
                jpaRepository.findAll(specification, Converter.pagination(pagination))
        );
    }

    public static <Entity, Key> long count(JpaRepositoryImplementation<Entity, Key> jpaRepository, Specification<Entity> specification) {
        return jpaRepository.count(specification);
    }

}
