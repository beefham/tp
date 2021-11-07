package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class OvertimeEqualPredicateTest {

    @Test
    public void equals() {
        int firstPredicateValue = 5;
        int secondPredicateValue = 5;
        int thirdPredicateValue = 10;
        OvertimeEqualPredicate firstPredicate = new OvertimeEqualPredicate(firstPredicateValue);
        OvertimeEqualPredicate secondPredicate = new OvertimeEqualPredicate(secondPredicateValue);
        OvertimeEqualPredicate thirdPredicate = new OvertimeEqualPredicate(thirdPredicateValue);

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
    public void test_overtimeEqual_returnsTrue() {
        OvertimeEqualPredicate predicate = new OvertimeEqualPredicate(5);

        // Equal overtime
        assertTrue(predicate.test(new PersonBuilder().withOvertime("5").build()));
    }

    @Test
    public void test_overtimeNotEqual_returnsFalse() {
        OvertimeEqualPredicate predicate = new OvertimeEqualPredicate(5);

        // More overtime
        assertFalse(predicate.test(new PersonBuilder().withOvertime("6").build()));

        // Less overtime
        assertFalse(predicate.test(new PersonBuilder().withOvertime("4").build()));
    }
}
