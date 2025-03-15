package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.PaymentInfo;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label placeholder1;
    @FXML
    private Label currentYear;
    @FXML
    private Label placeholder3;
    @FXML
    private Label placeholder4;
    @FXML
    private Label paymentFee;
    @FXML
    private Label paymentDate;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);

        placeholder1.setText("placeholder text 1");
        currentYear.setText("Current Year: " + person.getCurrentYear().value);
        placeholder3.setText("placeholder text 3");
        placeholder4.setText("placeholder text 4");
        PaymentInfo paymentInfo = person.getPaymentInfo();
        if (paymentInfo.getPaymentFee() == 0) {
            paymentFee.setManaged(false);
            paymentFee.setVisible(false);
        } else {
            paymentFee.setText("Tutoring Fee: $" + paymentInfo.getPaymentFee());
        }
        if (paymentInfo.getPaymentDate().isEmpty()) {
            paymentDate.setManaged(false);
            paymentDate.setVisible(false);
        } else {
            paymentDate.setText("Payment Date: " + paymentInfo.getPaymentDate());
        }

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
