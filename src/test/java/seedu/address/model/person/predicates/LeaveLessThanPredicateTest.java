package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class LeaveLessThanPredicateTest {

    @Test
    public void equals() {
        int firstPredicateValue = 5;
        int secondPredicateValue = 5;
        int thirdPredicateValue = 10;
        LeaveLessThanPredicate firstPredicate = new LeaveLessThanPredicate(firstPredicateValue);
        LeaveLessThanPredicate secondPredicate = new LeaveLessThanPredicate(secondPredicateValue);
        LeaveLessThanPredicate thirdPredicate = new LeaveLessThanPredicate(thirdPredicateValue);

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
    public void test_leaveLessThan_returnsTrue() {
        LeaveLessThanPredicate predicate = new LeaveLessThanPredicate(5);

        // Less leaves
        assertTrue(predicate.test(new PersonBuilder().withLeaves("4").build()));
    }

    @Test
    public void test_leaveNotLessThan_returnsFalse() {
        LeaveLessThanPredicate predicate = new LeaveLessThanPredicate(5);

        // Equal leaves
        assertFalse(predicate.test(new PersonBuilder().withLeaves("5").build()));

        // More leaves
        assertFalse(predicate.test(new PersonBuilder().withLeaves("6").build()));
    }
}
