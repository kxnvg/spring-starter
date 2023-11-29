package org.kxnvg.integration.database.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kxnvg.database.repository.CompanyRepository;
import org.kxnvg.entity.Company;
import org.kxnvg.integration.annotation.IT;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
class CompanyRepositoryTest {

    private static final Integer APPLE_ID = 5;
    private final EntityManager entityManager;
    private final TransactionTemplate transactionTemplate;
    private final CompanyRepository companyRepository;

    @Test
    void checkFindByQueries() {
        companyRepository.findByName("google");
        companyRepository.findAllByNameContainingIgnoreCase("a");
    }

    @Test
    void delete() {
        var maybeCompany = companyRepository.findById(APPLE_ID);
        assertTrue(maybeCompany.isPresent());
        maybeCompany.ifPresent(companyRepository::delete);
        entityManager.flush();
        assertTrue(companyRepository.findById(APPLE_ID).isEmpty());
    }

    @Test
    void testFindById() {
        Company company = entityManager.find(Company.class, 1);
        assertNotNull(company);
    }

//    @Test
//    void save() {
//        Company company = Company.builder()
//                .name("Apple")
//                .locales(Map.of(
//                        "ru", "Apple описание",
//                        "en", "Apple description"
//                ))
//                .build();
//        entityManager.persist(company);
//        assertNotNull(company.getId());
//    }

}