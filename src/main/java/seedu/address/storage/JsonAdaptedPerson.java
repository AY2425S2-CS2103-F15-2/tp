package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.CurrentGrade;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.PaymentInfo;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String currentGrade;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final int paymentFee;
    private final String paymentDate;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("currentGrade") String currentGrade), 
            @JsonProperty("tags") List<JsonAdaptedTag> tags, @JsonProperty("paymentFee") int paymentFee,
            @JsonProperty("paymentDate") String paymentDate) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.currentGrade = currentGrade;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.paymentFee = paymentFee;
        this.paymentDate = Objects.requireNonNullElse(paymentDate, "");
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        currentGrade = source.getCurrentGrade().value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        paymentFee = source.getPaymentInfo().getPaymentFee();
        paymentDate = source.getPaymentInfo().getPaymentDate();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (currentGrade == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CurrentGrade.class.getSimpleName()));
        }
        if (!CurrentGrade.isValidCurrentGrade(currentGrade)) {
            throw new IllegalValueException(CurrentGrade.MESSAGE_CONSTRAINTS);
        }
        final CurrentGrade modelCurrentGrade = new CurrentGrade(currentGrade);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        // IF paymentFee field is missing, it will be set as 0 by default. Therefore, no checks needed.
        if (!PaymentInfo.isValidFee(paymentFee)) {
            throw new IllegalValueException(PaymentInfo.MESSAGE_CONSTRAINTS_FEE);
        }
        if (paymentDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "[paymentDate]"));
        }
        if (!PaymentInfo.isValidDate(paymentDate)) {
            throw new IllegalValueException(PaymentInfo.MESSAGE_CONSTRAINTS_DATE);
        }
        final PaymentInfo paymentInfo = new PaymentInfo(paymentFee, paymentDate);

        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelTags, paymentInfo, modelCurrentGrade);
    }

}
