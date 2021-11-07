package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class SalaryIsLessThanEqualPredicateTest {

    @Test
    public void equals() {
        float firstPredicateValue = 5.5F;
        float secondPredicateValue = 5.5F;
        float thirdPredicateValue = 10.0F;
        SalaryIsLessThanEqualPredicate firstPredicate = new SalaryIsLessThanEqualPredicate(firstPredicateValue);
        SalaryIsLessThanEqualPredicate secondPredicate = new SalaryIsLessThanEqualPredicate(secondPredicateValue);
        SalaryIsLessThanEqualPredicate thirdPredicate = new SalaryIsLessThanEqualPredicate(thirdPredicateValue);

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
    public void test_hourlySalaryIsLessThanEqual_returnsTrue() {
        SalaryIsLessThanEqualPredicate predicate = new SalaryIsLessThanEqualPredicate(5.5F); // value = 5.5

        // Equal hourly salary
        assertTrue(predicate.test(new PersonBuilder().withHourlySalary("5.5").build()));

        // Less hourly salary
        assertTrue(predicate.test(new PersonBuilder().withHourlySalary("5.4").build()));
    }

    @Test
    public void test_hourlySalaryIsNotLessThanEqual_returnsFalse() {
        SalaryIsLessThanEqualPredicate predicate = new SalaryIsLessThanEqualPredicate(5.5F); // value = 5.5

        // More hourly salary
        assertFalse(predicate.test(new PersonBuilder().withHourlySalary("5.6").build()));
    }
}
