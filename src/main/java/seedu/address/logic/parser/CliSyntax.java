package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_TAG_REMOVE = new Prefix("t-/");
    // `PREFIX_PAYMENT_FEE` and `PREFIX_PAYMENT_DATE` is part of the Payment Command
    public static final Prefix PREFIX_PAYMENT_FEE = new Prefix("f/");
    public static final Prefix PREFIX_PAYMENT_DATE = new Prefix("d/");

}
