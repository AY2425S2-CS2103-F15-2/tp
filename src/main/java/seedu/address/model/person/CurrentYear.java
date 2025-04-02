package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's current year in the address book.
 * Guarantees: immutable; is always valid
 */
public class CurrentYear {

    public static final int MAX_LENGTH = 30;

    public static final String MESSAGE_CONSTRAINTS =
            String.format("Current Year should only contain alphanumeric characters and "
                    + "spaces and must not exceed %d characters. ", MAX_LENGTH)
                    + "As Current Year is an optional field, it could also be blank.";

    public static final String VALIDATION_REGEX = "[\\p{Alnum} ]*";
    public final String value;

    /**
     * Constructs a {@code CurrentYear}.
     *
     * @param currentYear A current year.
     */
    public CurrentYear(String currentYear) {
        requireNonNull(currentYear);
        checkArgument(isValidCurrentYear(currentYear), MESSAGE_CONSTRAINTS);
        this.value = currentYear;
    }

    public CurrentYear() {
        this.value = "";
    }

    /**
     * Returns true if a given string is a valid current year.
     */
    public static boolean isValidCurrentYear(String test) {
        return test.matches(VALIDATION_REGEX) && isValidLength(test);
    }

    /**
     * Check length of current year against limit
     * @param test
     * @return Returns true if given current year exceeds max length
     */
    public static boolean isValidLength(String test) {
        return test.length() <= MAX_LENGTH;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CurrentYear)) {
            return false;
        }

        CurrentYear otherCurrentYear = (CurrentYear) other;
        return value.equals(otherCurrentYear.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
