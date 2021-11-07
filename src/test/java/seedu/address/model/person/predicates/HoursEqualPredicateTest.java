package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class HoursEqualPredicateTest {

    @Test
    public void equals() {
        int firstPredicateValue = 5;
        int secondPredicateValue = 5;
        int thirdPredicateValue = 10;
        HoursEqualPredicate firstPredicate = new HoursEqualPredicate(firstPredicateValue);
        HoursEqualPredicate secondPredicate = new HoursEqualPredicate(secondPredicateValue);
        HoursEqualPredicate thirdPredicate = new HoursEqualPredicate(thirdPredicateValue);

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
    public void test_hoursWorkedEqual_returnsTrue() {
        HoursEqualPredicate predicate = new HoursEqualPredicate(5);

        // Equal hours
        assertTrue(predicate.test(new PersonBuilder().withHoursWorked("5").build()));
    }

    @Test
    public void test_hoursWorkedNotEqual_returnsFalse() {
        HoursEqualPredicate predicate = new HoursEqualPredicate(5);

        // More hours
        assertFalse(predicate.test(new PersonBuilder().withHoursWorked("6").build()));

        // Less hours
        assertFalse(predicate.test(new PersonBuilder().withHoursWorked("4").build()));
    }
}
