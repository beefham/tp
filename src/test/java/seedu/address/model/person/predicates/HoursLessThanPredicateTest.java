package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class HoursLessThanPredicateTest {

    @Test
    public void equals() {
        int firstPredicateValue = 5;
        int secondPredicateValue = 5;
        int thirdPredicateValue = 10;
        HoursLessThanPredicate firstPredicate = new HoursLessThanPredicate(firstPredicateValue);
        HoursLessThanPredicate secondPredicate = new HoursLessThanPredicate(secondPredicateValue);
        HoursLessThanPredicate thirdPredicate = new HoursLessThanPredicate(thirdPredicateValue);

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
    public void test_hoursWorkedLessThan_returnsTrue() {
        HoursLessThanPredicate predicate = new HoursLessThanPredicate(5);

        // Less hours
        assertTrue(predicate.test(new PersonBuilder().withHoursWorked("4").build()));
    }

    @Test
    public void test_hoursWorkedNotLessThan_returnsFalse() {
        HoursLessThanPredicate predicate = new HoursLessThanPredicate(5);

        // Equal hours
        assertFalse(predicate.test(new PersonBuilder().withHoursWorked("5").build()));

        // More hours
        assertFalse(predicate.test(new PersonBuilder().withHoursWorked("6").build()));

    }
}
