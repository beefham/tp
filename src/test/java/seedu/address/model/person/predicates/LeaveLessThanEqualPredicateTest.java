package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class LeaveLessThanEqualPredicateTest {

    @Test
    public void equals() {
        int firstPredicateValue = 5;
        int secondPredicateValue = 5;
        int thirdPredicateValue = 10;
        LeaveLessThanEqualPredicate firstPredicate = new LeaveLessThanEqualPredicate(firstPredicateValue);
        LeaveLessThanEqualPredicate secondPredicate = new LeaveLessThanEqualPredicate(secondPredicateValue);
        LeaveLessThanEqualPredicate thirdPredicate = new LeaveLessThanEqualPredicate(thirdPredicateValue);

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
    public void test_leaveLessThanEqual_returnsTrue() {
        LeaveLessThanEqualPredicate predicate = new LeaveLessThanEqualPredicate(5);

        // Equal leaves
        assertTrue(predicate.test(new PersonBuilder().withLeaves("5").build()));

        // Less leaves
        assertTrue(predicate.test(new PersonBuilder().withLeaves("4").build()));
    }

    @Test
    public void test_leaveNotLessThanEqual_returnsFalse() {
        LeaveLessThanEqualPredicate predicate = new LeaveLessThanEqualPredicate(5);

        // More leaves
        assertFalse(predicate.test(new PersonBuilder().withLeaves("6").build()));
    }
}
