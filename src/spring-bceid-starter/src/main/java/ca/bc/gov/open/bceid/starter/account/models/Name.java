package ca.bc.gov.open.bceid.starter.account.models;

public class Name {

    private String firstName;
    private String middleName;
    private String otherMiddleName;
    private String surname;
    private String initials;

    protected Name(Builder builder) {
        this.firstName = builder.firstName;
        this.middleName = builder.middleName;
        this.otherMiddleName = builder.otherMiddleName;
        this.surname = builder.surname;
        this.initials = builder.initials;
    }

    public Name(String firstName, String middleName, String otherMiddleName, String surname, String initials) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.otherMiddleName = otherMiddleName;
        this.surname = surname;
        this.initials = initials;
    }

    public String getFirstName() {
        return firstName;
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

    public static Name.Builder builder() {
        return new Name.Builder();
    }

    public static class Builder {

        private String firstName;
        private String middleName;
        private String otherMiddleName;
        private String surname;
        private String initials;


        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder middleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder otherMiddleName(String otherMiddleName) {
            this.otherMiddleName = otherMiddleName;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder initials(String initials) {
            this.initials = initials;
            return this;
        }


        public Name create() {
            return new Name(this);
        }

    }

}
