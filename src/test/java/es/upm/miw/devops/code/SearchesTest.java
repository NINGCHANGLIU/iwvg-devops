package es.upm.miw.devops.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;


class SearchesTest {

    @Test
    void testFindUserFamilyNameByUserNameDistinct() {
        assertThat(new Searches().findUserFamilyNameByUserNameDistinct("Paula").toList())
                .containsExactly("Torres");
    }

    @Test
    void testFindFractionNumeratorByUserFamilyName() {
        assertThat(new Searches().findFractionNumeratorByUserFamilyName("Torres").toList())
                .containsExactly(2, 4, 0, 1, 1);
    }

    @Test
    void testFindUserFamilyNameByFractionDenominator() {
        assertThat(new Searches().findUserFamilyNameByFractionDenominator(2).toList())
                .containsExactly("López", "Torres");
    }

    @Test
    void testOtherSearchesSmoke() {
        assertThat(new Searches().findUserFamilyNameInitialByAnyProperFraction()).isNotNull();
        assertThat(new Searches().findUserIdByAnyProperFraction()).isNotNull();
        assertThat(new Searches().findFractionMultiplicationByUserFamilyName("Torres")).isNotNull();
        assertThat(new Searches().findFirstFractionDivisionByUserId("2")).isNotNull();
        assertThat(new Searches().findFirstDecimalFractionByUserName("Oscar")).isNotNull();
        assertThat(new Searches().findUserIdByAllProperFraction()).isNotNull();
        assertThat(new Searches().findDecimalImproperFractionByUserName("Ana")).isNotNull();
        assertThat(new Searches().findFirstProperFractionByUserId("3")).isNotNull();
        assertThat(new Searches().findUserFamilyNameByImproperFraction()).isNotNull();
        assertThat(new Searches().findHighestFraction()).isNotNull();
        assertThat(new Searches().findUserNameByAnyImproperFraction()).isNotNull();
        assertThat(new Searches().findUserFamilyNameByAllNegativeSignFractionDistinct()).isNotNull();
        assertThat(new Searches().findDecimalFractionByUserName("Oscar")).isNotNull();
        assertThat(new Searches().findDecimalFractionByNegativeSignFraction()).isNotNull();
        assertThat(new Searches().findFractionAdditionByUserId("2")).isNotNull();
        assertThat(new Searches().findFirstFractionSubtractionByUserName("Oscar")).isNotNull();
    }

    @Test
    void testFindUserFamilyNameInitialByAnyProperFraction() {
        assertThat(new Searches().findUserFamilyNameInitialByAnyProperFraction().toList())
                .containsExactly("F", "B", "L");
    }

    @Test
    void testFindUserIdByAnyProperFraction() {
        assertThat(new Searches().findUserIdByAnyProperFraction().toList())
                .containsExactly("1", "2", "3", "5");
    }

    @Test
    void testFindFractionMultiplicationByUserFamilyName_Torres() {
        Fraction f = new Searches().findFractionMultiplicationByUserFamilyName("Torres");
        assertThat(f.getNumerator()).isZero();
        assertThat(f.getDenominator()).isZero(); // includes 0/0 from user 6
    }

    @Test
    void testFindFirstFractionDivisionByUserId_2() {
        Fraction f = new Searches().findFirstFractionDivisionByUserId("2");
        assertThat(f).isNotNull();
        assertThat(f.getNumerator()).isEqualTo(10);
        assertThat(f.getDenominator()).isEqualTo(-1);
    }

    @Test
    void testFindFirstDecimalFractionByUserName_Oscar() {
        Double d = new Searches().findFirstDecimalFractionByUserName("Oscar");
        assertThat(d).isEqualTo(0.0);
    }

    @Test
    void testFindUserIdByAllProperFraction() {
        assertThat(new Searches().findUserIdByAllProperFraction().toList())
                .containsExactly("5");
    }

    @Test
    void testFindDecimalImproperFractionByUserName_Ana() {
        var list = new Searches().findDecimalImproperFractionByUserName("Ana").toList();
        assertThat(list).hasSize(2);
        assertThat(list.get(0)).isEqualTo(2.0);
        assertThat(list.get(1)).isCloseTo(1.0 * 4 / 3.0, within(1e-9));
    }

    @Test
    void testFindFirstProperFractionByUserId_3() {
        Fraction f = new Searches().findFirstProperFractionByUserId("3");
        assertThat(f).isNotNull();
        assertThat(f.getNumerator()).isEqualTo(1);
        assertThat(f.getDenominator()).isEqualTo(5);
    }

    @Test
    void testFindUserFamilyNameByImproperFraction() {
        assertThat(new Searches().findUserFamilyNameByImproperFraction().toList())
                .containsExactly("Fernandez", "Blanco", "López", "Torres", "Torres");
    }

    @Test
    void testFindHighestFraction() {
        Fraction f = new Searches().findHighestFraction();
        assertThat(f).isNotNull();
        assertThat(f.getNumerator()).isEqualTo(0);
        assertThat(f.getDenominator()).isEqualTo(0); // NaN is considered greater by Double.compare
    }

    @Test
    void testFindUserNameByAnyImproperFraction() {
        assertThat(new Searches().findUserNameByAnyImproperFraction().toList())
                .containsExactly("Oscar", "Ana", "Oscar", "Paula", "Paula");
    }

    @Test
    void testFindUserFamilyNameByAllNegativeSignFractionDistinct() {
        assertThat(new Searches().findUserFamilyNameByAllNegativeSignFractionDistinct().toList())
                .isEmpty();
    }

    @Test
    void testFindDecimalFractionByUserName_Oscar() {
        assertThat(new Searches().findDecimalFractionByUserName("Oscar").toList())
                .containsExactly(0.0, 1.0, 2.0, 0.2, -0.5, 0.5, 1.0);
    }

    @Test
    void testFindDecimalFractionByNegativeSignFraction() {
        assertThat(new Searches().findDecimalFractionByNegativeSignFraction().toList())
                .containsExactly(-0.2, -0.5);
    }

    @Test
    void testFindFractionAdditionByUserId_2() {
        Fraction f = new Searches().findFractionAdditionByUserId("2");
        assertThat(f.getNumerator()).isEqualTo(218);
        assertThat(f.getDenominator()).isEqualTo(60);
    }

    @Test
    void testFindFirstFractionSubtractionByUserName_Oscar() {
        Fraction f = new Searches().findFirstFractionSubtractionByUserName("Oscar");
        assertThat(f.getNumerator()).isEqualTo(-1);
        assertThat(f.getDenominator()).isEqualTo(1);
    }

}



