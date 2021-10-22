package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEAVES_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEAVES_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Address;
import seedu.address.model.person.CalculatedPay;
import seedu.address.model.person.Email;
import seedu.address.model.person.HourlySalary;
import seedu.address.model.person.HoursWorked;
import seedu.address.model.person.Leave;
import seedu.address.model.person.LeavesTaken;
import seedu.address.model.person.Name;
import seedu.address.model.person.Overtime;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.tag.Tag;

public class AddLeaveCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Leave addedLeaves = new Leave("3");

    private Person createPersonWithAddedLeaves(Person personToAddLeavesTo, Leave addedLeaves) {
        Name name = personToAddLeavesTo.getName();
        Phone phone = personToAddLeavesTo.getPhone();
        Email email = personToAddLeavesTo.getEmail();
        Address address = personToAddLeavesTo.getAddress();
        Role role = personToAddLeavesTo.getRole();
        LeavesTaken leavesTaken = personToAddLeavesTo.getLeavesTaken();
        HourlySalary salary = personToAddLeavesTo.getSalary();
        HoursWorked hours = personToAddLeavesTo.getHoursWorked();
        Overtime overtime = personToAddLeavesTo.getOvertime();
        CalculatedPay calculatedPay = personToAddLeavesTo.getCalculatedPay();
        Set<Tag> tags = personToAddLeavesTo.getTags();

        Leave newLeaves = personToAddLeavesTo.getLeaves().addLeaves(addedLeaves);

        return new Person(name, phone, email, address, role, newLeaves, leavesTaken,
                salary, hours, overtime, calculatedPay, tags);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToAddLeavesTo = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        AddLeavesCommand addLeavesCommand = new AddLeavesCommand(INDEX_FIRST_PERSON, addedLeaves);

        Person personWithAddedLeaves = createPersonWithAddedLeaves(personToAddLeavesTo, addedLeaves);

        String expectedMessage =
                String.format(AddLeavesCommand.MESSAGE_SUCCESS, personWithAddedLeaves);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToAddLeavesTo, personWithAddedLeaves);

        assertCommandSuccess(addLeavesCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AddLeavesCommand addLeavesCommand = new AddLeavesCommand(outOfBoundIndex, addedLeaves);

        assertCommandFailure(addLeavesCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToAddLeavesTo = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        AddLeavesCommand addLeavesCommand = new AddLeavesCommand(INDEX_FIRST_PERSON, addedLeaves);

        Person personWithAddedLeaves = createPersonWithAddedLeaves(personToAddLeavesTo, addedLeaves);

        String expectedMessage =
                String.format(AddLeavesCommand.MESSAGE_SUCCESS, personWithAddedLeaves);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.setPerson(personToAddLeavesTo, personWithAddedLeaves);

        assertCommandSuccess(addLeavesCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // Ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AddLeavesCommand addLeavesCommand = new AddLeavesCommand(outOfBoundIndex, addedLeaves);

        assertCommandFailure(addLeavesCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AddLeavesCommand standardCommand = new AddLeavesCommand(INDEX_FIRST_PERSON, new Leave(VALID_LEAVES_AMY));

        // Same values -> returns true
        AddLeavesCommand commandWithSameValues = new AddLeavesCommand(INDEX_FIRST_PERSON, new Leave(VALID_LEAVES_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // Same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // Different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // Different index -> returns false
        assertFalse(standardCommand.equals(new AddLeavesCommand(INDEX_SECOND_PERSON, new Leave(VALID_LEAVES_AMY))));

        // Different number of leaves -> returns false
        assertFalse(standardCommand.equals(new AddLeavesCommand(INDEX_FIRST_PERSON, new Leave(VALID_LEAVES_BOB))));
    }
}
