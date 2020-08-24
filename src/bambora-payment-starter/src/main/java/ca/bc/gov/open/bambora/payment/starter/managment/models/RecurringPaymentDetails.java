package ca.bc.gov.open.bambora.payment.starter.managment.models;

public class RecurringPaymentDetails {
    private String orderNumber;
    private String endUserId;
    private String echoData;
    private String redirectURL;

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getEndUserId() {
        return endUserId;
    }

    public String getEchoData() {
        return echoData;
    }

    public String getRedirectURL() {
        return redirectURL;
    }

    protected RecurringPaymentDetails(Builder builder) {
        this.orderNumber = builder.orderNumber;
        this.endUserId = builder.endUserId;
        this.echoData = builder.echoData;
        this.redirectURL = builder.redirectURL;
    }

    public static RecurringPaymentDetails.Builder builder() {
        return new RecurringPaymentDetails.Builder();
    }

    public static class Builder {

        private String orderNumber;
        private String endUserId;
        private String echoData;
        private String redirectURL;

        public Builder orderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
            return this;
        }

        public Builder endUserId(String endUserId) {
            this.endUserId = endUserId;
            return this;
        }

        public Builder echoData(String echoData) {
            this.echoData = echoData;
            return this;
        }

        public Builder redirectURL(String redirectURL) {
            this.redirectURL = redirectURL;
            return this;
        }

        public RecurringPaymentDetails create() {
            return new RecurringPaymentDetails(this);
        }

    }
}
