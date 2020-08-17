package ca.bc.gov.open.bceid.starter.account.models;

import org.joda.time.DateTime;

public class IndividualIdentity {

    private Name name;
    private DateTime dateOfBirth;
    private Address residentialAddress;
    private Address mailingAddress;

    protected IndividualIdentity(Builder builder) {
        this.name = builder.name;
        this.dateOfBirth = builder.dateOfBirth;
        this.residentialAddress = builder.residentialAddress;
        this.mailingAddress = builder.mailingAddress;
    }

    public IndividualIdentity(Name name, DateTime dateOfBirth, Address residentialAddress, Address mailingAddress) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.residentialAddress = residentialAddress;
        this.mailingAddress = mailingAddress;
    }

    public Name getName() {
        return name;
    }

    public DateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public Address getResidentialAddress() {
        return residentialAddress;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public static IndividualIdentity.Builder builder() {
        return new IndividualIdentity.Builder();
    }

    public static class Builder {

        private Name name;
        private DateTime dateOfBirth;
        private Address residentialAddress;
        private Address mailingAddress;

        public Builder name(Name name) {
            this.name = name;
            return this;
        }

        public Builder dateOfBirth(DateTime dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder residentialAddress(Address residentialAddress) {
            this.residentialAddress = residentialAddress;
            return this;
        }

        public Builder mailingAddress(Address mailingAddress) {
            this.mailingAddress = mailingAddress;
            return this;
        }

       public IndividualIdentity create() {
            return new IndividualIdentity(this);
       }

    }

}
