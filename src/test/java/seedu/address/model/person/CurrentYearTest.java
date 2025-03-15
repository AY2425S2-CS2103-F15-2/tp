package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CurrentYearTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CurrentYear(null));
    }

    @Test
    public void constructor_invalidCurrentYear_throwsIllegalArgumentException() {
        String invalidCurrentYear = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidCurrentYear));
    }

    @Test
    public void isValidCurrentYear() {
        // null current year
        assertThrows(NullPointerException.class, () -> CurrentYear.isValidCurrentYear(null));

        // invalid current year
        assertFalse(CurrentYear.isValidCurrentYear("^")); // only non-alphanumeric characters
        assertFalse(CurrentYear.isValidCurrentYear("2021*")); // contains non-alphanumeric characters

        // valid current year
        assertTrue(CurrentYear.isValidCurrentYear("")); // empty string
        assertTrue(CurrentYear.isValidCurrentYear(" ")); // spaces only
        assertTrue(CurrentYear.isValidCurrentYear("2025")); // numbers only
        assertTrue(CurrentYear.isValidCurrentYear("Year 2025")); // alphanumeric characters
        assertTrue(CurrentYear.isValidCurrentYear("Year Twenty Twenty Five")); // with spaces
    }

    @Test
    public void equals() {
        CurrentYear currentYear = new CurrentYear("Academic Year 2025");

        // same values -> returns true
        assertTrue(currentYear.equals(new CurrentYear("Academic Year 2025")));

        // same object -> returns true
        assertTrue(currentYear.equals(currentYear));

        // null -> returns false
        assertFalse(currentYear.equals(null));

        // different types -> returns false
        assertFalse(currentYear.equals(5.0f));

        // different values -> returns false
        assertFalse(currentYear.equals(new CurrentYear("Academic Year 2026")));
    }
}
