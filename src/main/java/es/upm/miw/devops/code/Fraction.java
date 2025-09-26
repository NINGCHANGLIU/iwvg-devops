package es.upm.miw.devops.code;

/**
 * Fraction domain model.
 * Concepts:
 * - Proper fractions: numerator absolute value is less than denominator absolute value
 * - Improper fractions: numerator absolute value is greater than or equal to denominator absolute value
 * - Two fractions are equivalent if cross products are equal: a/b == c/d iff a*d == b*c
 *
 * This model does not auto-simplify fractions. Operations keep raw numerator/denominator.
 */
public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction() {
        this(1, 1);
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    /**
     * Returns the decimal value. It may return +/-Infinity or NaN when denominator is 0.
     */
    public double decimal() {
        return (double) numerator / denominator;
    }

    /**
     * A fraction is proper if |numerator| < |denominator|.
     */
    public boolean isProper() {
        return Math.abs(this.numerator) < Math.abs(this.denominator);
    }

    /**
     * A fraction is improper if |numerator| >= |denominator|.
     */
    public boolean isImproper() {
        return Math.abs(this.numerator) >= Math.abs(this.denominator);
    }

    /**
     * Check if this fraction is equivalent to other: a/b == c/d iff a*d == b*c.
     */
    public boolean isEquivalent(Fraction other) {
        if (other == null) {
            return false;
        }
        // Use long to avoid intermediate integer overflow for large values
        long left = (long) this.numerator * other.denominator;
        long right = (long) this.denominator * other.numerator;
        return left == right;
    }

    /**
     * Add two fractions: a/b + c/d = (a*d + c*b) / (b*d)
     */
    public Fraction add(Fraction other) {
        int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    /**
     * Multiply two fractions: a/b * c/d = (a*c) / (b*d)
     */
    public Fraction multiply(Fraction other) {
        return new Fraction(this.numerator * other.numerator, this.denominator * other.denominator);
    }

    /**
     * Divide two fractions: (a/b) / (c/d) = (a*d) / (b*c)
     */
    public Fraction divide(Fraction other) {
        return new Fraction(this.numerator * other.denominator, this.denominator * other.numerator);
    }

    @Override
    public String toString() {
        return "Fraction{" +
                "numerator=" + numerator +
                ", denominator=" + denominator +
                '}';
    }
}


