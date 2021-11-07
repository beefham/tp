package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class HoursMoreThanPredicateTest {

    @Test
    public void equals() {
        int firstPredicateValue = 5;
        int secondPredicateValue = 5;
        int thirdPredicateValue = 10;
        HoursMoreThanPredicate firstPredicate = new HoursMoreThanPredicate(firstPredicateValue);
        HoursMoreThanPredicate secondPredicate = new HoursMoreThanPredicate(secondPredicateValue);
        HoursMoreThanPredicate thirdPredicate = new HoursMoreThanPredicate(thirdPredicateValue);

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
        HoursMoreThanPredicate predicate = new HoursMoreThanPredicate(5);

        // More hours
        assertTrue(predicate.test(new PersonBuilder().withHoursWorked("6").build()));
    }

    @Test
    public void test_hoursWorkedNotEqual_returnsFalse() {
        HoursMoreThanPredicate predicate = new HoursMoreThanPredicate(5);

        // Equal hours
        assertFalse(predicate.test(new PersonBuilder().withHoursWorked("5").build()));

        // Less hours
        assertFalse(predicate.test(new PersonBuilder().withHoursWorked("4").build()));
    }
}
