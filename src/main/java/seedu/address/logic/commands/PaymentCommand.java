package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYMENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYMENT_FEE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.PaymentInfo;
import seedu.address.model.person.Person;

/**
 * Changes the {@code PaymentInfo} of an existing person in the address book.
 */
public class PaymentCommand extends Command {
    public static final String COMMAND_WORD = "payment";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the payment information"
            + " of the person identified "
            + "by the index number used in the person listing. "
            + "Existing payment  will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) ["
            + PREFIX_PAYMENT_FEE + "FEE] [" + PREFIX_PAYMENT_DATE + "PAYMENT_DATE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PAYMENT_FEE + "900 " + PREFIX_PAYMENT_DATE + "13-03-2025";
    public static final String MESSAGE_ADD_PAYMENT_SUCCESS = "Added payment information to Person: %1$s";
    public static final String MESSAGE_UPDATE_PAYMENT_SUCCESS = "Updated payment information for Person: %1$s";
    public static final String MESSAGE_DELETE_PAYMENT_SUCCESS = "Removed payment information from Person: %1$s";
    public static final String MESSAGE_SAME_PAYMENT_SUCCESS = "No changes to payment information for Person: %1$s";

    private final Index index;
    private final PaymentInfo paymentInfo;

    /**
     * Creates a PaymentCommand to update a specific {@code Person} using {@code index}.
     *
     * @param index of the person in the filtered person list to edit the remark
     * @param paymentFee of the person to be updated to
     * @param paymentDate of the person to be updated to
     */
    public PaymentCommand(Index index, int paymentFee, String paymentDate) {
        requireAllNonNull(index, paymentFee, paymentDate);

        this.index = index;
        this.paymentInfo = new PaymentInfo(paymentFee, paymentDate);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getEduLevel(), personToEdit.getTags(), paymentInfo);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(personToEdit, editedPerson));
    }

    /**
     * Generates a command execution success message based on how the {@code paymentInfo} changed based on the
     * different between {@code personToEdit} and {@code editedPerson}.
     */
    private String generateSuccessMessage(Person personToEdit, Person editedPerson) {
        PaymentInfo originalPaymentInfo = personToEdit.getPaymentInfo();
        PaymentInfo newPaymentInfo = editedPerson.getPaymentInfo();
        if (originalPaymentInfo.equals(newPaymentInfo)) {
            return String.format(MESSAGE_SAME_PAYMENT_SUCCESS, editedPerson);
        } else if (originalPaymentInfo.getPaymentFee() == 0 && originalPaymentInfo.getPaymentDate().isEmpty()) {
            return String.format(MESSAGE_ADD_PAYMENT_SUCCESS, editedPerson);
        } else if (newPaymentInfo.getPaymentFee() == 0 && newPaymentInfo.getPaymentDate().isEmpty()) {
            return String.format(MESSAGE_DELETE_PAYMENT_SUCCESS, editedPerson);
        } else {
            return String.format(MESSAGE_UPDATE_PAYMENT_SUCCESS, editedPerson);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PaymentCommand)) {
            return false;
        }

        PaymentCommand e = (PaymentCommand) other;
        return index.equals(e.index)
                && paymentInfo.equals(e.paymentInfo);
    }
}
