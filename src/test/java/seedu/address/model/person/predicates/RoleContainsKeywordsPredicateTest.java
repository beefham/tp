package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class RoleContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Arrays.asList("Admin", "IT");
        List<String> secondPredicateKeywordList = Arrays.asList("IT");

        RoleContainsKeywordsPredicate firstPredicate =
                new RoleContainsKeywordsPredicate(firstPredicateKeywordList);

        RoleContainsKeywordsPredicate firstPredicateCopy =
                new RoleContainsKeywordsPredicate(firstPredicateKeywordList);

        RoleContainsKeywordsPredicate secondPredicate =
                new RoleContainsKeywordsPredicate(secondPredicateKeywordList);

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
    public void test_roleContainsKeywords_returnsTrue() {
        // One keyword
        RoleContainsKeywordsPredicate predicate =
                new RoleContainsKeywordsPredicate(Collections.singletonList("Admin"));
        assertTrue(predicate.test(new PersonBuilder().withRole("Admin Assistant").build()));
        assertTrue(predicate.test(new PersonBuilder().withRole("Chief Admin").build()));

        // Multiple keywords
        predicate = new RoleContainsKeywordsPredicate(Arrays.asList("Technician", "Finance"));
        assertTrue(predicate.test(new PersonBuilder().withRole("Finance System Technician").build()));

        // One matching keyword
        assertTrue(predicate.test(new PersonBuilder().withRole("Finance Manager").build()));

        // Mixed-case keywords
        predicate = new RoleContainsKeywordsPredicate(Arrays.asList("aDMin", "finANce"));
        assertTrue(predicate.test(new PersonBuilder().withRole("Chief Finance").build()));
        assertTrue(predicate.test(new PersonBuilder().withRole("IT Admin").build()));
    }

    @Test
    public void test_roleDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        RoleContainsKeywordsPredicate predicate = new RoleContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withRole("Software Engineer").build()));

        // Non-matching keyword
        predicate = new RoleContainsKeywordsPredicate(Arrays.asList("Finance"));
        assertFalse(predicate.test(new PersonBuilder().withRole("Software Engineer").build()));

    }
}
