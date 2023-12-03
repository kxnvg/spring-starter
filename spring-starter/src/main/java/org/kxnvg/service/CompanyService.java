package org.kxnvg.service;

import lombok.RequiredArgsConstructor;
import org.kxnvg.database.repository.CompanyRepository;
import org.kxnvg.dto.CompanyReadDto;
import org.kxnvg.entity.Company;
import org.kxnvg.listener.entity.AccessType;
import org.kxnvg.listener.entity.EntityEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                    return new CompanyReadDto(id, null);
                });
    }
}
