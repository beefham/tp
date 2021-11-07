package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class SalaryIsEqualPredicateTest {

    @Test
    public void equals() {
        float firstPredicateValue = 5.5F;
        float secondPredicateValue = 5.5F;
        float thirdPredicateValue = 10.0F;
        SalaryIsEqualPredicate firstPredicate = new SalaryIsEqualPredicate(firstPredicateValue);
        SalaryIsEqualPredicate secondPredicate = new SalaryIsEqualPredicate(secondPredicateValue);
        SalaryIsEqualPredicate thirdPredicate = new SalaryIsEqualPredicate(thirdPredicateValue);

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
    public void test_hourlySalaryIsEqual_returnsTrue() {
        SalaryIsEqualPredicate predicate = new SalaryIsEqualPredicate(5.5F); // value = 5.5

        // Equal hourly salary
        assertTrue(predicate.test(new PersonBuilder().withHourlySalary("5.5").build()));
    }

    @Test
    public void test_hourlySalaryIsNotEqual_returnsFalse() {
        SalaryIsEqualPredicate predicate = new SalaryIsEqualPredicate(5.5F); // value = 5.5

        // More hourly salary
        assertFalse(predicate.test(new PersonBuilder().withHourlySalary("5.6").build()));

        // Less hourly salary
        assertFalse(predicate.test(new PersonBuilder().withHourlySalary("5.4").build()));
    }
}
