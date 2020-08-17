package ca.bc.gov.open.bceid.starter.account.models;

public class Name {

    private String firstname;
    private String middleName;
    private String otherMiddleName;
    private String surname;
    private String initials;

    public Name(String firstname, String middleName, String otherMiddleName, String surname, String initials) {
        this.firstname = firstname;
        this.middleName = middleName;
        this.otherMiddleName = otherMiddleName;
        this.surname = surname;
        this.initials = initials;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getOtherMiddleName() {
        return otherMiddleName;
    }

    public String getSurname() {
        return surname;
    }

    public String getInitials() {
        return initials;
    }
}
