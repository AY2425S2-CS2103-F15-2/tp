package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.CurrentGrade;
import seedu.address.model.person.CurrentYear;
import seedu.address.model.person.EduLevel;
import seedu.address.model.person.Email;
import seedu.address.model.person.ExpectedGrade;
import seedu.address.model.person.Name;
import seedu.address.model.person.PaymentInfo;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_EXP_GRADE = "A";
    public static final String DEFAULT_EDULEVEL = "Bachelor";

    private Name name;
    private Phone phone;
    private Email email;
    private EduLevel eduLevel;
    private Address address;
    private CurrentYear currentYear;
    private CurrentGrade currentGrade;
    private ExpectedGrade expectedGrade;

    private Set<Tag> tags;
    private PaymentInfo paymentInfo;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        expectedGrade = new ExpectedGrade(DEFAULT_EXP_GRADE);
        currentYear = new CurrentYear();
        currentGrade = new CurrentGrade();
        eduLevel = new EduLevel(DEFAULT_EDULEVEL);
        tags = new HashSet<>();
        paymentInfo = new PaymentInfo();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        currentYear = personToCopy.getCurrentYear();
        currentGrade = personToCopy.getCurrentGrade();
        expectedGrade = personToCopy.getExpectedGrade();
        eduLevel = personToCopy.getEduLevel();
        tags = new HashSet<>(personToCopy.getTags());
        paymentInfo = personToCopy.getPaymentInfo();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code ExpectedGrade} of the {@code Person} that we are building.
     */
    public PersonBuilder withExpectedGrade(String expectedGrade) {
        this.expectedGrade = new ExpectedGrade(expectedGrade);
        return this;
    }

    /**
     * Sets the {@code CurrentYear} of the {@code Person} that we are building.
     */
    public PersonBuilder withCurrentYear(String currentYear) {
        this.currentYear = new CurrentYear(currentYear);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withCurrentGrade(String currentGrade) {
        this.currentGrade = new CurrentGrade(currentGrade);
        return this;
    }

    /**
     * Sets the {@code PaymentInfo} of the {@code Person} that we are building, with no initial value.
     */
    public PersonBuilder withPaymentInfo() {
        this.paymentInfo = new PaymentInfo();
        return this;
    }

    /**
     * Sets the {@code PaymentInfo} of the {@code Person} that we are building, containing only the Payment Fee.
     */
    public PersonBuilder withPaymentInfo(int paymentFee) {
        this.paymentInfo = new PaymentInfo(paymentFee);
        return this;
    }

    /**
     * Sets the {@code PaymentInfo} of the {@code Person} that we are building, containing only the Payment Date.
     */
    public PersonBuilder withPaymentInfo(String paymentDate) {
        this.paymentInfo = new PaymentInfo(paymentDate);
        return this;
    }

    /**
     * Sets the {@code PaymentInfo} of the {@code Person} that we are building, containing Payment Fee & Date.
     */
    public PersonBuilder withPaymentInfo(int paymentFee, String paymentDate) {
        this.paymentInfo = new PaymentInfo(paymentFee, paymentDate);
        return this;
    }

    /**
     * Sets the {@code EduLevel} of the {@code Person} that we are building.
     */
    public PersonBuilder withEduLevel(String eduLevel) {
        this.eduLevel = new EduLevel(eduLevel);
        return this;
    }

    public Person build() {
        return new Person(
                name, phone, email, address, eduLevel, currentYear, currentGrade, expectedGrade, tags, paymentInfo);
    }

}
