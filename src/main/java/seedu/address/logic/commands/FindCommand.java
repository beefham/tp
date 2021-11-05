package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOURLYSALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOURSWORKED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEAVE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OVERTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.CalculatedPay;
import seedu.address.model.person.Email;
import seedu.address.model.person.HourlySalary;
import seedu.address.model.person.HoursWorked;
import seedu.address.model.person.LeaveBalance;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.tag.Tag;

/**
 * Finds and lists all persons in HeRon that passes the given predicate.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all employees who meets "
            + "all the specified conditions and displays them as a list with index numbers.\n"
            + "Each condition is tagged with a prefix just like the add command.\n"
            + "For example, to search by name, use n/[name].\n"
            + "All parameters are optional. To search by salary, use only one of the comparison operators "
            + "'>', '<', '>=', '<=' or '=' together with a number.\n"
            + "To search by date, you can specify a combination of individual dates (YYYY-MM-DD) "
            + "or date ranges. (YYYY-MM-DD:YYYY-MM-DD)\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_ROLE + "ROLE] "
            + "[" + PREFIX_LEAVE + "(>/>=/</<=/=)LEAVES] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_HOURLYSALARY + "(>/>=/</<=/=)SALARY] "
            + "[" + PREFIX_OVERTIME + "(>/>=/</<=/=)OVERTIME] "
            + "[" + PREFIX_HOURSWORKED + "HOURS WORKED] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " n/Alex Ben r/CEO d/2021-10-30 s/>=10";

    private final Predicate<Person> predicate;

    public FindCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    /**
     * Used for testing purposes to allow comparison of the predicates.
     * @return The Predicate belonging to this FindCommand.
     */
    public Predicate<Person> getPredicate() {
        return predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);

        if (model.getFilteredPersonList().size() == 0) {
            // If the filtered list is empty, display a default person
            HashSet<Tag> egTags = new HashSet<>();
            egTags.add(new Tag("example"));
            Person examplePerson = new Person(new Name("Example person"), new Phone("62353535"),
                    new Email("example@empl.com"), new Address("Example Street, Blk 404"),
                    new Role("Exemplar"), new LeaveBalance("69"),
                    new HourlySalary("666"), new HoursWorked("420"),
                    new CalculatedPay("0"), egTags);
            model.setViewingPerson(examplePerson);
        } else {
            // Else, display the first person
            Person firstPerson = model.getFilteredPersonList().get(0);
            model.setViewingPerson(firstPerson);
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
