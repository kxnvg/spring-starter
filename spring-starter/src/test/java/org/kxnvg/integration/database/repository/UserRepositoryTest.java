package org.kxnvg.integration.database.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.kxnvg.database.repository.UserRepository;
import org.kxnvg.dto.PersonalInfo;
import org.kxnvg.dto.PersonalInfo2;
import org.kxnvg.dto.UserFilter;
import org.kxnvg.entity.Role;
import org.kxnvg.entity.User;
import org.kxnvg.integration.annotation.IT;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
class UserRepositoryTest {

    private final UserRepository userRepository;

    @Test
    void checkBatch() {
        List<User> users = userRepository.findAll();
        userRepository.updateCompanyAndRole(users);
    }

    @Test
    void checkJdbcTemplate() {
        List<PersonalInfo> users = userRepository.findAllByCompanyIdAndRole(1, Role.USER);
        assertTrue(users.size() == 1);
    }

    @Test
    @Commit
    void checkAuditing() {
        User ivan = userRepository.findById(1L).get();
        ivan.setBirthDate(ivan.getBirthDate().plusYears(1));
        userRepository.flush();
    }

    @Test
    void checkCustomImplementation() {
        UserFilter filter = new UserFilter(
                null, "%ov%", LocalDate.now()
        );
        List<User> users = userRepository.findAllByFilter(filter);
    }

    @Test
    void checkProjections() {
        List<PersonalInfo2> users = userRepository.findAllByCompanyId(1);
        assertEquals(2, users.size());
    }

    @Test
    void checkPageable() {
        PageRequest pageable = PageRequest.of(1, 2, Sort.by("id"));
        Page<User> page = userRepository.findAllBy(pageable);
        page.forEach(user -> System.out.println(user.getId()));

        while (page.hasNext()) {
            page = userRepository.findAllBy(page.nextPageable());
            page.forEach(user -> System.out.println(user.getCompany().getName()));
        }
    }

    @Test
    void checkFirstTop() {
        Optional<User> topUsers = userRepository.findTopByOrderByIdDesc();
        assertTrue(topUsers.isPresent());
        topUsers.ifPresent(user -> assertEquals(5L, user.getId()));
    }

    @Test
    void checkUpdate() {
        User ivan = userRepository.getById(1L);
        assertSame(Role.ADMIN, ivan.getRole());

        int resultCount = userRepository.updateRole(Role.USER, 1L, 5L);
        assertEquals(2, resultCount);

        User sameIvan = userRepository.getById(1L);
        assertSame(Role.USER, sameIvan.getRole());
    }

    @Test
    void checkQueries() {
        List<User> users = userRepository.findAllBy("a", "ov");
        assertEquals(3, users.size());
    }

}