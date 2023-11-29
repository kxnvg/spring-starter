package org.kxnvg.database.repository;

import org.kxnvg.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query("SELECT c FROM Company c WHERE c.name = :name")
    Optional<Company> findByName(String name);

    List<Company> findAllByNameContainingIgnoreCase(String fragment);
}
