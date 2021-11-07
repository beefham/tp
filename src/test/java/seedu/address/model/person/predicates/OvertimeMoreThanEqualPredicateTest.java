package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class OvertimeMoreThanEqualPredicateTest {

    @Test
    public void equals() {
        int firstPredicateValue = 5;
        int secondPredicateValue = 5;
        int thirdPredicateValue = 10;
        OvertimeMoreThanEqualPredicate firstPredicate = new OvertimeMoreThanEqualPredicate(firstPredicateValue);
        OvertimeMoreThanEqualPredicate secondPredicate = new OvertimeMoreThanEqualPredicate(secondPredicateValue);
        OvertimeMoreThanEqualPredicate thirdPredicate = new OvertimeMoreThanEqualPredicate(thirdPredicateValue);

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
    public void test_overtimeMoreThanEqual_returnsTrue() {
        OvertimeMoreThanEqualPredicate predicate = new OvertimeMoreThanEqualPredicate(5);

        // Equal overtime
        assertTrue(predicate.test(new PersonBuilder().withOvertime("5").build()));

        // More overtime
        assertTrue(predicate.test(new PersonBuilder().withOvertime("6").build()));
    }

    @Test
    public void test_overtimeNotMoreThanEqual_returnsFalse() {
        OvertimeMoreThanEqualPredicate predicate = new OvertimeMoreThanEqualPredicate(5);


        // Less overtime
        assertFalse(predicate.test(new PersonBuilder().withOvertime("4").build()));
    }
}
