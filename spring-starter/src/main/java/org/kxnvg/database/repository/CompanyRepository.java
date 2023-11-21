package org.kxnvg.database.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kxnvg.database.ConnectionPool;
import org.kxnvg.entity.Company;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CompanyRepository implements CrudRepository<Integer, Company> {

    private final ConnectionPool connectionPool;
    private final List<ConnectionPool> pools;
    @Value("${database.pool.size}")
    private final Integer poolSize;

    @Override
    public Optional<Company> findById(Integer id) {
        log.info("findById method...");
        return Optional.of(new Company(id, null, Collections.emptyMap()));
    }

    @Override
    public void delete(Company entity) {
        log.info("delete method...");
    }
}
