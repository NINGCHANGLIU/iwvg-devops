package es.upm.miw.devops.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
}


