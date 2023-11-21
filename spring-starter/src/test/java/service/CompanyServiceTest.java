package service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kxnvg.database.repository.CrudRepository;
import org.kxnvg.dto.CompanyReadDto;
import org.kxnvg.entity.Company;
import org.kxnvg.listener.entity.EntityEvent;
import org.kxnvg.CompanyService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    private static final Integer COMPANY_ID = 1;

    @Mock
    private CrudRepository<Integer, Company> companyRepository;
    @Mock
    private ApplicationEventPublisher eventPublisher;
    @InjectMocks
    private CompanyService companyService;

    @Test
    void testFindById() {
        when(companyRepository.findById(COMPANY_ID)).thenReturn(Optional.of(new Company(COMPANY_ID)));
        var actualResult = companyService.findById(COMPANY_ID);

        assertTrue(actualResult.isPresent());
        var expectedResult = new CompanyReadDto(COMPANY_ID);
        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
        verify(eventPublisher).publishEvent(any(EntityEvent.class));
    }
}
