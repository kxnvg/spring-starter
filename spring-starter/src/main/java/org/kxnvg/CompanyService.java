package org.kxnvg;

import lombok.RequiredArgsConstructor;
import org.kxnvg.database.repository.CrudRepository;
import org.kxnvg.dto.CompanyReadDto;
import org.kxnvg.entity.Company;
import org.kxnvg.listener.entity.AccessType;
import org.kxnvg.listener.entity.EntityEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CrudRepository<Integer, Company> companyRepository;
    private final ApplicationEventPublisher eventPublisher;

    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                    return new CompanyReadDto(entity.id());
                });
    }
}
