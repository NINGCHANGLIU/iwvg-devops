package es.upm.miw.devops.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UsersDatabaseTest {

    @Test
    void testFindAllContainsExpectedUsers() {
        long count = new UsersDatabase().findAll().count();
        assertThat(count).isEqualTo(6);
    }
}


