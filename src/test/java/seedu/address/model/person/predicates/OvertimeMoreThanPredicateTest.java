package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class OvertimeMoreThanPredicateTest {

    @Test
    public void equals() {
        int firstPredicateValue = 5;
        int secondPredicateValue = 5;
        int thirdPredicateValue = 10;
        OvertimeMoreThanPredicate firstPredicate = new OvertimeMoreThanPredicate(firstPredicateValue);
        OvertimeMoreThanPredicate secondPredicate = new OvertimeMoreThanPredicate(secondPredicateValue);
        OvertimeMoreThanPredicate thirdPredicate = new OvertimeMoreThanPredicate(thirdPredicateValue);

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
    public void test_overtimeMoreThan_returnsTrue() {
        OvertimeMoreThanPredicate predicate = new OvertimeMoreThanPredicate(5);

        // More overtime
        assertTrue(predicate.test(new PersonBuilder().withOvertime("6").build()));
    }

    @Test
    public void test_overtimeNotMoreThan_returnsFalse() {
        OvertimeMoreThanPredicate predicate = new OvertimeMoreThanPredicate(5);

        // Equal overtime
        assertFalse(predicate.test(new PersonBuilder().withOvertime("5").build()));

        // Less overtime
        assertFalse(predicate.test(new PersonBuilder().withOvertime("4").build()));
    }
}
