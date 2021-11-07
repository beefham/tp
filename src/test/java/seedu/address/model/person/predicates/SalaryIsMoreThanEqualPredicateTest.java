package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class SalaryIsMoreThanEqualPredicateTest {

    @Test
    public void equals() {
        float firstPredicateValue = 5.5F;
        float secondPredicateValue = 5.5F;
        float thirdPredicateValue = 10.0F;
        SalaryIsMoreThanEqualPredicate firstPredicate = new SalaryIsMoreThanEqualPredicate(firstPredicateValue);
        SalaryIsMoreThanEqualPredicate secondPredicate = new SalaryIsMoreThanEqualPredicate(secondPredicateValue);
        SalaryIsMoreThanEqualPredicate thirdPredicate = new SalaryIsMoreThanEqualPredicate(thirdPredicateValue);

        // same object -> return true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same value -> return true
        assertTrue(firstPredicate.equals(secondPredicate));

        // different type -> return false
        assertFalse(firstPredicate.equals(5));

        // different value -> return false
        assertFalse(firstPredicate.equals(thirdPredicate));

        // null -> return false
        assertFalse(secondPredicate.equals(null));

    }

    @Test
    public void test_hourlySalaryIsMoreThanEqual_returnsTrue() {
        SalaryIsMoreThanEqualPredicate predicate = new SalaryIsMoreThanEqualPredicate(5.5F); // value = 5.5

        // Equal hourly salary
        assertTrue(predicate.test(new PersonBuilder().withHourlySalary("5.5").build()));

        // More hourly salary
        assertTrue(predicate.test(new PersonBuilder().withHourlySalary("5.6").build()));
    }

    @Test
    public void test_hourlySalaryIsNotMoreThanEqual_returnsFalse() {
        SalaryIsMoreThanEqualPredicate predicate = new SalaryIsMoreThanEqualPredicate(5.5F); // value = 5.5

        // Less hourly salary
        assertFalse(predicate.test(new PersonBuilder().withHourlySalary("5.4").build()));
    }
}
