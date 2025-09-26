package es.upm.miw.devops.code;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void testFullNameAndInitials() {
        User user = new User("id", "John", "Doe", List.of());
        assertThat(user.fullName()).isEqualTo("John Doe");
        assertThat(user.initials()).isEqualTo("J.");
    }

    @Test
    void testAddFraction() {
        User user = new User();
        user.setName("Alice");
        user.setFamilyName("Smith");
        user.addFraction(new Fraction(1, 2));
        assertThat(user.getFractions()).hasSize(1);
    }
}


