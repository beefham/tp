package seedu.address.model.person.predicates;

import java.util.function.Predicate;

import seedu.address.model.person.Person;

public class OvertimeEqualPredicate implements Predicate<Person> {
    private final int value;

    public OvertimeEqualPredicate(int value) {
        this.value = value;
    }

    /**
     * Tests if the person given has overtime equal to the given value.
     * @param person The person whose overtime is to be tested
     * @return true if the person's overtime is equal to the given value.
     */
    @Override
    public boolean test(Person person) {
        return person.getOvertime().value == this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OvertimeEqualPredicate // instanceof handles nulls
                && value == ((OvertimeEqualPredicate) other).value); // state check
    }
}
