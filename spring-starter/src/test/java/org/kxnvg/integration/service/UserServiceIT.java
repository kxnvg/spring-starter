package org.kxnvg.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.kxnvg.service.UserService;
import org.kxnvg.integration.annotation.IT;
import org.kxnvg.database.ConnectionPool;
import org.springframework.test.context.TestConstructor;

@IT
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
public class UserServiceIT {

    private final UserService userService;
    private final ConnectionPool pool1;

    @Test
    void test() {

    }
}
