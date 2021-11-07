package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class OvertimeLessThanPredicateTest {

    @Test
    public void equals() {
        int firstPredicateValue = 5;
        int secondPredicateValue = 5;
        int thirdPredicateValue = 10;
        OvertimeLessThanPredicate firstPredicate = new OvertimeLessThanPredicate(firstPredicateValue);
        OvertimeLessThanPredicate secondPredicate = new OvertimeLessThanPredicate(secondPredicateValue);
        OvertimeLessThanPredicate thirdPredicate = new OvertimeLessThanPredicate(thirdPredicateValue);

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
    public void test_overtimeLessThan_returnsTrue() {
        OvertimeLessThanPredicate predicate = new OvertimeLessThanPredicate(5);

        // Less overtime
        assertTrue(predicate.test(new PersonBuilder().withOvertime("4").build()));
    }

    @Test
    public void test_overtimeNotLessThan_returnsFalse() {
        OvertimeLessThanPredicate predicate = new OvertimeLessThanPredicate(5);

        // Equal overtime
        assertFalse(predicate.test(new PersonBuilder().withOvertime("5").build()));

        // More overtime
        assertFalse(predicate.test(new PersonBuilder().withOvertime("6").build()));
    }
}
