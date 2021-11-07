package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class OvertimeLessThanEqualPredicateTest {

    @Test
    public void equals() {
        int firstPredicateValue = 5;
        int secondPredicateValue = 5;
        int thirdPredicateValue = 10;
        OvertimeLessThanEqualPredicate firstPredicate = new OvertimeLessThanEqualPredicate(firstPredicateValue);
        OvertimeLessThanEqualPredicate secondPredicate = new OvertimeLessThanEqualPredicate(secondPredicateValue);
        OvertimeLessThanEqualPredicate thirdPredicate = new OvertimeLessThanEqualPredicate(thirdPredicateValue);

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
    public void test_overtimeLessThanEqual_returnsTrue() {
        OvertimeLessThanEqualPredicate predicate = new OvertimeLessThanEqualPredicate(5);

        // Equal overtime
        assertTrue(predicate.test(new PersonBuilder().withOvertime("5").build()));

        // Less overtime
        assertTrue(predicate.test(new PersonBuilder().withOvertime("4").build()));
    }

    @Test
    public void test_overtimeNotLessThanEqual_returnsFalse() {
        OvertimeLessThanEqualPredicate predicate = new OvertimeLessThanEqualPredicate(5);

        // More overtime
        assertFalse(predicate.test(new PersonBuilder().withOvertime("6").build()));
    }
}
