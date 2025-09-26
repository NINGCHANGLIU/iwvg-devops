package es.upm.miw.devops.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class FractionTest {

    @Test
    void testDecimal() {
        assertThat(new Fraction(1, 2).decimal()).isCloseTo(0.5, within(1e-5));
        assertThat(new Fraction(1, 0).decimal()).isInfinite();
        assertThat(new Fraction(0, 0).decimal()).isNaN();
    }

    @Test
    void testProperImproper() {
        assertThat(new Fraction(1, 2).isProper()).isTrue();
        assertThat(new Fraction(3, 2).isImproper()).isTrue();
        assertThat(new Fraction(-1, -2).isProper()).isTrue();
    }

    @Test
    void testEquivalent() {
        assertThat(new Fraction(1, 2).isEquivalent(new Fraction(2, 4))).isTrue();
        assertThat(new Fraction(1, 2).isEquivalent(new Fraction(3, 4))).isFalse();
        assertThat(new Fraction(1, 2).isEquivalent(null)).isFalse();
    }

    @Test
    void testAddMultiplyDivide() {
        Fraction a = new Fraction(1, 2);
        Fraction b = new Fraction(1, 3);
        Fraction sum = a.add(b); // 1/2 + 1/3 = 5/6
        assertThat(sum.getNumerator()).isEqualTo(5);
        assertThat(sum.getDenominator()).isEqualTo(6);

        Fraction mul = a.multiply(b); // 1/6
        assertThat(mul.getNumerator()).isEqualTo(1);
        assertThat(mul.getDenominator()).isEqualTo(6);

        Fraction div = a.divide(b); // (1/2) / (1/3) = 3/2
        assertThat(div.getNumerator()).isEqualTo(3);
        assertThat(div.getDenominator()).isEqualTo(2);
    }

    @Test
    void testConstructorsGettersSettersAndToString() {
        Fraction f = new Fraction();
        assertThat(f.getNumerator()).isEqualTo(1);
        assertThat(f.getDenominator()).isEqualTo(1);

        f.setNumerator(7);
        f.setDenominator(-9);
        assertThat(f.getNumerator()).isEqualTo(7);
        assertThat(f.getDenominator()).isEqualTo(-9);

        String text = f.toString();
        assertThat(text).contains("numerator=7");
        assertThat(text).contains("denominator=-9");
    }
}


