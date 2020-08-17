package ca.bc.gov.open.bceid.starter.account.models;

public class Address {

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String postal;
    private String province;
    private String country;
    private String unstructuredAddress;

    public Address(String addressLine1, String addressLine2, String city, String postal, String province, String country, String unstructuredAddress) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.postal = postal;
        this.province = province;
        this.country = country;
        this.unstructuredAddress = unstructuredAddress;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getPostal() {
        return postal;
    }

    public String getProvince() {
        return province;
    }

    public String getCountry() {
        return country;
    }

    public String getUnstructuredAddress() {
        return unstructuredAddress;
    }
}
