package org.kxnvg.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.kxnvg.dto.UserCreateEditDto;
import org.kxnvg.dto.UserReadDto;
import org.kxnvg.entity.Role;
import org.kxnvg.integration.IntegrationTestBase;
import org.kxnvg.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
public class UserServiceIT extends IntegrationTestBase {

    private static final Long USER_1 = 1L;
    private static final Integer COMPANY_1 = 1;

    private final UserService userService;

    @Test
    void testFindAll() {
        List<UserReadDto> result = userService.findAll();
        assertTrue(result.size() == 5);
    }

    @Test
    void testFindById() {
        Optional<UserReadDto> maybeUser = userService.findById(USER_1);
        assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(user -> assertEquals("ivan@gmail.com", user.getUsername()));
    }

//    @Test
//    void testCreate() {
//        UserCreateEditDto userDto = new UserCreateEditDto(
//                "test@gmail.com",
//                LocalDate.now(),
//                "Test",
//                "Test",
//                Role.ADMIN,
//                COMPANY_1
//        );
//        UserReadDto actualResult = userService.create(userDto);
//
//        assertEquals(userDto.getUsername(), actualResult.getUsername());
//        assertEquals(userDto.getBirthDate(), actualResult.getBirthDate());
//        assertEquals(userDto.getFirstname(), actualResult.getFirstname());
//        assertEquals(userDto.getLastname(), actualResult.getLastname());
//        assertEquals(userDto.getCompanyId(), actualResult.getCompany().id());
//        assertSame(userDto.getRole(), actualResult.getRole());
//    }
//
//    @Test
//    void testUpdate() {
//        UserCreateEditDto userDto = new UserCreateEditDto(
//                "test@gmail.com",
//                LocalDate.now(),
//                "Test",
//                "Test",
//                Role.ADMIN,
//                COMPANY_1
//        );
//        Optional<UserReadDto> actualResult = userService.update(USER_1, userDto);
//
//        assertTrue(actualResult.isPresent());
//        actualResult.ifPresent(user -> {
//            assertEquals(userDto.getUsername(), user.getUsername());
//            assertEquals(userDto.getBirthDate(), user.getBirthDate());
//            assertEquals(userDto.getFirstname(), user.getFirstname());
//            assertEquals(userDto.getLastname(), user.getLastname());
//            assertEquals(userDto.getCompanyId(), user.getCompany().id());
//            assertSame(userDto.getRole(), user.getRole());
//        });
//    }

    @Test
    void testDelete() {
        assertFalse(userService.delete(-123L));
        assertTrue(userService.delete(USER_1));
    }
}
