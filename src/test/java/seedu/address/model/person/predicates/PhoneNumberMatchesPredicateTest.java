package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class PhoneNumberMatchesPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Arrays.asList("91234567", "81828384");
        List<String> secondPredicateKeywordList = Arrays.asList("81828384");

        PhoneNumberMatchesPredicate firstPredicate =
                new PhoneNumberMatchesPredicate(firstPredicateKeywordList);

        PhoneNumberMatchesPredicate firstPredicateCopy =
                new PhoneNumberMatchesPredicate(firstPredicateKeywordList);

        PhoneNumberMatchesPredicate secondPredicate =
                new PhoneNumberMatchesPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different type -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keyword list -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_phoneNumberMatches_returnsTrue() {
        // One keyword
        PhoneNumberMatchesPredicate predicate =
                new PhoneNumberMatchesPredicate(Collections.singletonList("91234567"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("91234567").build()));

        // Multiple keywords
        predicate = new PhoneNumberMatchesPredicate(Arrays.asList("91234567", "98765432"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("98765432").build()));


    }

    @Test
    public void test_phoneDoesNotMatch_returnsFalse() {
        // Zero keywords
        PhoneNumberMatchesPredicate predicate = new PhoneNumberMatchesPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withPhone("62353535").build()));

        // Non-matching keyword
        predicate = new PhoneNumberMatchesPredicate(Arrays.asList("91234567", "94435589"));
        assertFalse(predicate.test(new PersonBuilder().withPhone("90283138").build()));

    }
}
