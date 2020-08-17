package ca.bc.gov.open.bceid.starter.account.models;

import org.joda.time.DateTime;

public class IndividualIdentity {

    private Name name;
    private DateTime dateOfBirth;
    private Address residentialAddress;
    private Address mailingAddress;

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
}
