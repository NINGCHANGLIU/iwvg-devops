package es.upm.miw.devops.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UsersDatabaseTest {

    @Test
    void testFindAllContainsExpectedUsers() {
        UsersDatabase db = new UsersDatabase();
        assertThat(db.findAll().count()).isEqualTo(6);
        assertThat(db.findAll().filter(u -> "2".equals(u.getId())).findFirst()).isPresent();
    }
}


