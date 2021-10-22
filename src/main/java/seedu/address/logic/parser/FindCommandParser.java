package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOURLYSALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOURSWORKED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEAVE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OVERTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.AddressContainsKeywordsPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.HoursEqualPredicate;
import seedu.address.model.person.predicates.HoursLessThanEqualPredicate;
import seedu.address.model.person.predicates.HoursLessThanPredicate;
import seedu.address.model.person.predicates.HoursMoreThanEqualPredicate;
import seedu.address.model.person.predicates.HoursMoreThanPredicate;
import seedu.address.model.person.predicates.LeaveEqualPredicate;
import seedu.address.model.person.predicates.LeaveLessThanEqualPredicate;
import seedu.address.model.person.predicates.LeaveLessThanPredicate;
import seedu.address.model.person.predicates.LeaveMoreThanEqualPredicate;
import seedu.address.model.person.predicates.LeaveMoreThanPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.OvertimeEqualPredicate;
import seedu.address.model.person.predicates.OvertimeLessThanEqualPredicate;
import seedu.address.model.person.predicates.OvertimeLessThanPredicate;
import seedu.address.model.person.predicates.OvertimeMoreThanEqualPredicate;
import seedu.address.model.person.predicates.OvertimeMoreThanPredicate;
import seedu.address.model.person.predicates.PersonIsPaidPredicate;
import seedu.address.model.person.predicates.PhoneNumberMatchesPredicate;
import seedu.address.model.person.predicates.RoleContainsKeywordsPredicate;
import seedu.address.model.person.predicates.SalaryIsEqualPredicate;
import seedu.address.model.person.predicates.SalaryIsLessThanEqualPredicate;
import seedu.address.model.person.predicates.SalaryIsLessThanPredicate;
import seedu.address.model.person.predicates.SalaryIsMoreThanEqualPredicate;
import seedu.address.model.person.predicates.SalaryIsMoreThanPredicate;
import seedu.address.model.person.predicates.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    private enum CompareType {
        LESS_THAN_EQUAL, LESS_THAN, EQUAL, MORE_THAN, MORE_THAN_EQUAL
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_ROLE,
                        PREFIX_LEAVE, PREFIX_HOURLYSALARY, PREFIX_HOURSWORKED, PREFIX_TAG, PREFIX_OVERTIME);

        String preamble = argMultimap.getPreamble();
        if (trimmedArgs.isEmpty() && preamble.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArrayList<Predicate<Person>> filters = new ArrayList<>();

        // Check if prefix exists and add the relevant predicate into the list of filters
        if (preamble.contains("unpaid")) {
            filters.add(new PersonIsPaidPredicate());
        }
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String[] nameKeywords = argMultimap.getValue(PREFIX_NAME).get().split("\\s+");
            filters.add(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String[] phoneKeyNumbers = argMultimap.getValue(PREFIX_PHONE).get().split("\\s+");
            filters.add(new PhoneNumberMatchesPredicate(Arrays.asList(phoneKeyNumbers)));
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            String[] tagKeywords = argMultimap.getValue(PREFIX_TAG).get().split("\\s+");
            filters.add(new TagContainsKeywordsPredicate(Arrays.asList(tagKeywords)));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            String[] emailKeywords = argMultimap.getValue(PREFIX_EMAIL).get().split("\\s+");
            filters.add(new EmailContainsKeywordsPredicate(Arrays.asList(emailKeywords)));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            String[] addressKeywords = argMultimap.getValue(PREFIX_ADDRESS).get().split("\\s+");
            filters.add(new AddressContainsKeywordsPredicate(Arrays.asList(addressKeywords)));
        }
        if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            String[] roleKeywords = argMultimap.getValue(PREFIX_ROLE).get().split("\\s+");
            filters.add(new RoleContainsKeywordsPredicate(Arrays.asList(roleKeywords)));
        }
        if (argMultimap.getValue(PREFIX_HOURLYSALARY).isPresent()) {
            String keyValue = argMultimap.getValue(PREFIX_HOURLYSALARY).get();
            Predicate<Person> personPredicate = getSalaryComparisonPredicate(keyValue);
            filters.add(personPredicate);
        }
        if (argMultimap.getValue(PREFIX_OVERTIME).isPresent()) {
            String keyValue = argMultimap.getValue(PREFIX_OVERTIME).get();
            Predicate<Person> personPredicate = getOvertimeComparisonPredicate(keyValue);
            filters.add(personPredicate);
        }
        if (argMultimap.getValue(PREFIX_LEAVE).isPresent()) {
            String keyValue = argMultimap.getValue(PREFIX_LEAVE).get();
            Predicate<Person> personPredicate = getLeaveComparisonPredicate(keyValue);
            filters.add(personPredicate);
        }
        if (argMultimap.getValue(PREFIX_HOURSWORKED).isPresent()) {
            String keyValue = argMultimap.getValue(PREFIX_HOURSWORKED).get();
            Predicate<Person> personPredicate = getHoursComparisonPredicate(keyValue);
            filters.add(personPredicate);
        }
        return new FindCommand(combinePredicates(filters));
    }

    /**
     * Combines the list of predicates in a list to a single predicate for the FindCommand to take in
     *
     * @param predicateList The list of predicates to combine
     */
    private Predicate<Person> combinePredicates(List<Predicate<Person>> predicateList) {
        return predicateList.stream()
                .reduce(x -> true, (predicate1, predicate2) -> predicate1.and(predicate2));
    }

    /**
     * Used for parsing the input given by the user for finding with respect to salary
     * @param input A string describing the condition for the salary of the person.
     *              It should take the form of (comparator)(number), where the comparator is any of:
     *              ">", "<", ">=", "<=", "="
     *              Valid examples: >=5, <3.25, =6.00
     *              Invalid examples: =>5, ==4.50, 6
     *
     * @return A Predicate which checks if the person passes the given condition as described in the input.
     */
    private Predicate<Person> getSalaryComparisonPredicate(String input) throws ParseException {
        try {
            CompareType compareType = parseComparator(input);
            float value = Float.parseFloat(getComparisonValue(input, compareType));
            if (compareType == CompareType.MORE_THAN) {
                return new SalaryIsMoreThanPredicate(value);
            } else if (compareType == CompareType.MORE_THAN_EQUAL) {
                return new SalaryIsMoreThanEqualPredicate(value);
            } else if (compareType == CompareType.EQUAL) {
                return new SalaryIsEqualPredicate(value);
            } else if (compareType == CompareType.LESS_THAN) {
                return new SalaryIsLessThanPredicate(value);
            } else if (compareType == CompareType.LESS_THAN_EQUAL) {
                return new SalaryIsLessThanEqualPredicate(value);
            } else {
                throw new ParseException("Invalid comparison type!");
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException | ParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Used for parsing the input given by the user for finding with respect to overtime.
     *
     * @param input A string describing the condition for the overtime of the person.
     * @return A Predicate which checks if the person passes the given condition as described in the input.
     */
    private Predicate<Person> getOvertimeComparisonPredicate(String input) throws ParseException {
        try {
            CompareType compareType = parseComparator(input);
            int value = Integer.parseInt(getComparisonValue(input, compareType));
            if (compareType == CompareType.MORE_THAN) {
                return new OvertimeMoreThanPredicate(value);
            } else if (compareType == CompareType.MORE_THAN_EQUAL) {
                return new OvertimeMoreThanEqualPredicate(value);
            } else if (compareType == CompareType.EQUAL) {
                return new OvertimeEqualPredicate(value);
            } else if (compareType == CompareType.LESS_THAN) {
                return new OvertimeLessThanPredicate(value);
            } else if (compareType == CompareType.LESS_THAN_EQUAL) {
                return new OvertimeLessThanEqualPredicate(value);
            } else {
                throw new ParseException("Invalid comparison type!");
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException | ParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Used for parsing the input given by the user for finding with respect to leaves.
     *
     * @param input A string describing the condition for the overtime of the person.
     * @return A Predicate which checks if the person passes the given condition as described in the input.
     */
    private Predicate<Person> getLeaveComparisonPredicate(String input) throws ParseException {
        try {
            CompareType compareType = parseComparator(input);
            int value = Integer.parseInt(getComparisonValue(input, compareType));
            if (compareType == CompareType.MORE_THAN) {
                return new LeaveMoreThanPredicate(value);
            } else if (compareType == CompareType.MORE_THAN_EQUAL) {
                return new LeaveMoreThanEqualPredicate(value);
            } else if (compareType == CompareType.EQUAL) {
                return new LeaveEqualPredicate(value);
            } else if (compareType == CompareType.LESS_THAN) {
                return new LeaveLessThanPredicate(value);
            } else if (compareType == CompareType.LESS_THAN_EQUAL) {
                return new LeaveLessThanEqualPredicate(value);
            } else {
                throw new ParseException("Invalid comparison type!");
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException | ParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Used for parsing the input given by the user for finding with respect to leaves.
     *
     * @param input A string describing the condition for the overtime of the person.
     * @return A Predicate which checks if the person passes the given condition as described in the input.
     */
    private Predicate<Person> getHoursComparisonPredicate(String input) throws ParseException {
        try {
            CompareType compareType = parseComparator(input);
            int value = Integer.parseInt(getComparisonValue(input, compareType));
            if (compareType == CompareType.MORE_THAN) {
                return new HoursMoreThanPredicate(value);
            } else if (compareType == CompareType.MORE_THAN_EQUAL) {
                return new HoursMoreThanEqualPredicate(value);
            } else if (compareType == CompareType.EQUAL) {
                return new HoursEqualPredicate(value);
            } else if (compareType == CompareType.LESS_THAN) {
                return new HoursLessThanPredicate(value);
            } else if (compareType == CompareType.LESS_THAN_EQUAL) {
                return new HoursLessThanEqualPredicate(value);
            } else {
                throw new ParseException("Invalid comparison type!");
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException | ParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }
    /**
     * Parses the initial user input and gets the type of comparison the user wants to make.
     * ">=" for more than or equal, ">" for strictly more than
     * "<=" for less than or equal, "<" for strictly less than
     * "=" for exactly equal.
     *
     * If any of the single character comparisons are used, this parser checks if the character
     * directly following it is an integer. Otherwise, the comparison is considered invalid.
     * For example, ">5" is valid but "==" and "><" are not.
     *
     * @param input The parsed user input from the argument multimap
     * @return The relevant comparison type
     */
    private CompareType parseComparator(String input) throws ParseException {
        String comparator = input.substring(0, 2);
        if (comparator.equals(">=")) {
            return CompareType.MORE_THAN_EQUAL;
        } else if (comparator.equals("<=")) {
            return CompareType.LESS_THAN_EQUAL;
        } else {
            try {
                // checks that the character following a single character comparator is a number
                Integer.parseInt(comparator.substring(1, 2));
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            char singleCharComparator = comparator.charAt(0);
            if (singleCharComparator == '>') {
                return CompareType.MORE_THAN;
            } else if (singleCharComparator == '<') {
                return CompareType.LESS_THAN;
            } else if (singleCharComparator == '=') {
                return CompareType.EQUAL;
            }
        }
        throw new ParseException("Could not find comparison operator!");
    }

    /**
     * Attempts to get the value to compare to as a String.
     * The String is parsed based on the type of command it is parsing.
     * @param input The parsed user input from the argument multimap
     * @param type The comparison type
     * @return A String representing the value to compare to
     */
    private String getComparisonValue(String input, CompareType type) throws ParseException {
        assert (input.length() > 2);
        if (type == CompareType.MORE_THAN_EQUAL || type == CompareType.LESS_THAN_EQUAL) {
            return input.substring(2);
        } else if (type == CompareType.EQUAL || type == CompareType.LESS_THAN || type == CompareType.MORE_THAN) {
            return input.substring(1);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }
}
