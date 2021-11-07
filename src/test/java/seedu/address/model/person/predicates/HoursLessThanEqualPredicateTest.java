package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class HoursLessThanEqualPredicateTest {

    @Test
    public void equals() {
        int firstPredicateValue = 5;
        int secondPredicateValue = 5;
        int thirdPredicateValue = 10;
        HoursLessThanEqualPredicate firstPredicate = new HoursLessThanEqualPredicate(firstPredicateValue);
        HoursLessThanEqualPredicate secondPredicate = new HoursLessThanEqualPredicate(secondPredicateValue);
        HoursLessThanEqualPredicate thirdPredicate = new HoursLessThanEqualPredicate(thirdPredicateValue);

        // same object
        assertTrue(firstPredicate.equals(firstPredicate));

        // same value
        assertTrue(firstPredicate.equals(secondPredicate));

        // different type
        assertFalse(firstPredicate.equals(5));

        // different value
        assertFalse(firstPredicate.equals(thirdPredicate));

        // null
        assertFalse(secondPredicate.equals(null));

    }

    @Test
    public void test_hoursWorkedLessOrEqual_returnsTrue() {
        HoursLessThanEqualPredicate predicate = new HoursLessThanEqualPredicate(5);

        // Equal hours
        assertTrue(predicate.test(new PersonBuilder().withHoursWorked("5").build()));

        // Less hours
        assertTrue(predicate.test(new PersonBuilder().withHoursWorked("4").build()));
    }

    @Test
    public void test_hoursWorkedNotLessOrEqual_returnsFalse() {
        HoursLessThanEqualPredicate predicate = new HoursLessThanEqualPredicate(5);

        // More hours
        assertFalse(predicate.test(new PersonBuilder().withHoursWorked("6").build()));

    }
}
