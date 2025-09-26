package es.upm.miw.devops.code;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Functional searches over the seeded in-memory users database.
 */
public class Searches {

    public Stream<String> findUserFamilyNameByUserNameDistinct(String userName) {
        return new UsersDatabase().findAll()
                .filter(user -> userName.equals(user.getName()))
                .map(User::getFamilyName)
                .distinct();
    }

    public Stream<Integer> findFractionNumeratorByUserFamilyName(String userFamilyName) {
        return new UsersDatabase().findAll()
                .filter(user -> userFamilyName.equals(user.getFamilyName()))
                .flatMap(user -> user.getFractions().stream().filter(Objects::nonNull))
                .map(Fraction::getNumerator);
    }

    public Stream<String> findUserFamilyNameByFractionDenominator(int fractionDenominator) {
        return new UsersDatabase().findAll()
                .filter(user -> user.getFractions().stream()
                        .anyMatch(f -> fractionDenominator == f.getDenominator()))
                .map(User::getFamilyName);
    }

    public Stream<String> findUserFamilyNameInitialByAnyProperFraction() {
        return new UsersDatabase().findAll()
                .filter(user -> user.getFractions().stream().anyMatch(Fraction::isProper))
                .map(User::getFamilyName)
                .filter(n -> n != null && !n.isEmpty())
                .map(n -> n.substring(0, 1))
                .distinct();
    }

    public Stream<String> findUserIdByAnyProperFraction() {
        return new UsersDatabase().findAll()
                .filter(user -> user.getFractions().stream().anyMatch(Fraction::isProper))
                .map(User::getId);
    }

    public Fraction findFractionMultiplicationByUserFamilyName(String familyName) {
        int[] acc = new UsersDatabase().findAll()
                .filter(user -> familyName.equals(user.getFamilyName()))
                .flatMap(user -> user.getFractions().stream())
                .reduce(new int[]{1, 1}, (a, f) -> new int[]{a[0] * f.getNumerator(), a[1] * f.getDenominator()}, (a, b) -> new int[]{a[0] * b[0], a[1] * b[1]});
        return new Fraction(acc[0], acc[1]);
    }

    /**
     * Divide all fractions of the user with given id from left to right.
     * Returns null if the user has fewer than two fractions.
     */
    public Fraction findFractionDivisionByUserId(String id) {
        Optional<Fraction> result = new UsersDatabase().findAll()
                .filter(user -> id.equals(user.getId()))
                .findFirst()
                .flatMap(u -> u.getFractions().stream().reduce((a, b) -> a.divide(b)));
        return result.orElse(null);
    }

    /**
     * Deprecated: kept for backward compatibility. Previously divided only the first two fractions.
     */
    @Deprecated
    public Fraction findFirstFractionDivisionByUserId(String id) {
        return findFractionDivisionByUserId(id);
    }

    public Double findFirstDecimalFractionByUserName(String name) {
        return new UsersDatabase().findAll()
                .filter(user -> name.equals(user.getName()))
                .flatMap(user -> user.getFractions().stream())
                .map(Fraction::decimal)
                .findFirst()
                .orElse(null);
    }

    public Stream<String> findUserIdByAllProperFraction() {
        return new UsersDatabase().findAll()
                .filter(user -> !user.getFractions().isEmpty() && user.getFractions().stream().allMatch(Fraction::isProper))
                .map(User::getId);
    }

    public Stream<Double> findDecimalImproperFractionByUserName(String name) {
        return new UsersDatabase().findAll()
                .filter(user -> name.equals(user.getName()))
                .flatMap(user -> user.getFractions().stream())
                .filter(Fraction::isImproper)
                .map(Fraction::decimal);
    }

    public Fraction findFirstProperFractionByUserId(String id) {
        return new UsersDatabase().findAll()
                .filter(user -> id.equals(user.getId()))
                .flatMap(user -> user.getFractions().stream())
                .filter(Fraction::isProper)
                .findFirst()
                .orElse(null);
    }

    public Stream<String> findUserFamilyNameByImproperFraction() {
        return new UsersDatabase().findAll()
                .filter(user -> user.getFractions().stream().anyMatch(Fraction::isImproper))
                .map(User::getFamilyName);
    }

    public Fraction findHighestFraction() {
        return new UsersDatabase().findAll()
                .flatMap(user -> user.getFractions().stream())
                .max(Comparator.comparingDouble(Fraction::decimal))
                .orElse(null);
    }

    public Stream<String> findUserNameByAnyImproperFraction() {
        return new UsersDatabase().findAll()
                .filter(user -> user.getFractions().stream().anyMatch(Fraction::isImproper))
                .map(User::getName);
    }

    public Stream<String> findUserFamilyNameByAllNegativeSignFractionDistinct() {
        return new UsersDatabase().findAll()
                .filter(user -> !user.getFractions().isEmpty() && user.getFractions().stream().allMatch(f -> f.decimal() < 0))
                .map(User::getFamilyName)
                .distinct();
    }

    public Stream<Double> findDecimalFractionByUserName(String name) {
        return new UsersDatabase().findAll()
                .filter(user -> name.equals(user.getName()))
                .flatMap(user -> user.getFractions().stream())
                .map(Fraction::decimal);
    }

    public Stream<Double> findDecimalFractionByNegativeSignFraction() {
        return new UsersDatabase().findAll()
                .flatMap(user -> user.getFractions().stream())
                .filter(f -> f.decimal() < 0)
                .map(Fraction::decimal);
    }

    public Fraction findFractionAdditionByUserId(String id) {
        int[] acc = new UsersDatabase().findAll()
                .filter(user -> id.equals(user.getId()))
                .flatMap(user -> user.getFractions().stream())
                .reduce(new int[]{0, 1}, (a, f) -> new int[]{a[0] * f.getDenominator() + f.getNumerator() * a[1], a[1] * f.getDenominator()}, (a, b) -> new int[]{a[0] * b[1] + b[0] * a[1], a[1] * b[1]});
        return new Fraction(acc[0], acc[1]);
    }

    public Fraction findFirstFractionSubtractionByUserName(String name) {
        Optional<Fraction> result = new UsersDatabase().findAll()
                .filter(user -> name.equals(user.getName()))
                .findFirst()
                .flatMap(u -> u.getFractions().stream().limit(2)
                        .reduce((a, b) -> new Fraction(a.getNumerator() * b.getDenominator() - b.getNumerator() * a.getDenominator(), a.getDenominator() * b.getDenominator())));
        return result.orElse(null);
    }
}



