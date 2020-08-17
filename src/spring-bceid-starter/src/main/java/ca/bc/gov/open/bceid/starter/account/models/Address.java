package ca.bc.gov.open.bceid.starter.account.models;

public class Address {

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String postal;
    private String province;
    private String country;
    private String unstructuredAddress;

    protected Address(Builder builder) {
        this.addressLine1 = builder.addressLine1;
        this.addressLine2 = builder.addressLine2;
        this.city = builder.city;
        this.postal = builder.postal;
        this.province = builder.province;
        this.country = builder.country;
        this.unstructuredAddress = builder.unstructuredAddress;
    }

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

    public static Address.Builder builder() {
        return new Address.Builder();
    }

    public static class Builder {

        private String addressLine1;
        private String addressLine2;
        private String city;
        private String postal;
        private String province;
        private String country;
        private String unstructuredAddress;

        public Builder addressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
            return this;
        }

        public Builder addressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder postal(String postal) {
            this.postal = postal;
            return this;
        }

        public Builder province(String province) {
            this.province = province;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder unstructuredAddress(String unstructuredAddress) {
            this.unstructuredAddress = unstructuredAddress;
            return this;
        }

        public Address create() {
            return new Address(this);
        }

    }

}
