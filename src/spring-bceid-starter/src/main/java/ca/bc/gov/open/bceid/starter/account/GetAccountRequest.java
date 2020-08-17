package ca.bc.gov.open.bceid.starter.account;

import ca.bceid.webservices.client.v9.BCeIDAccountTypeCode;
import org.apache.commons.lang3.StringUtils;

public class GetAccountRequest {

    private String id;
    private BCeIDAccountTypeCode bCeIDAccountTypeCode;
    private String requesterId;
    private BCeIDAccountTypeCode requesterBCeIDAccountTypeCode;

    private GetAccountRequest(String id, BCeIDAccountTypeCode bCeIDAccountTypeCode) {
        this.id = id;
        this.bCeIDAccountTypeCode = bCeIDAccountTypeCode;
        this.requesterId = id;
        this.requesterBCeIDAccountTypeCode = bCeIDAccountTypeCode;
    }

    public String getId() {
        return id;
    }

    public BCeIDAccountTypeCode getbCeIDAccountTypeCode() {
        return bCeIDAccountTypeCode;
    }

    public String getRequesterId() {
        return requesterId;
    }

    public BCeIDAccountTypeCode getRequesterBCeIDAccountTypeCode() {
        return requesterBCeIDAccountTypeCode;
    }

    public static GetAccountRequest BusinessSelfRequest(String id) {
        if(StringUtils.isBlank(id)) throw new IllegalArgumentException("Id value is required");
        return new GetAccountRequest(id, BCeIDAccountTypeCode.BUSINESS);
    }

    public static GetAccountRequest EdsSelfRequest(String id) {
        if(StringUtils.isBlank(id)) throw new IllegalArgumentException("Id value is required");
        return new GetAccountRequest(id, BCeIDAccountTypeCode.EDS);
    }

    public static GetAccountRequest IndividualSelfRequest(String id) {
        if(StringUtils.isBlank(id)) throw new IllegalArgumentException("Id value is required");
        return new GetAccountRequest(id, BCeIDAccountTypeCode.INDIVIDUAL);
    }

    public static GetAccountRequest InternalSelfRequest(String id) {
        if(StringUtils.isBlank(id)) throw new IllegalArgumentException("Id value is required");
        return new GetAccountRequest(id, BCeIDAccountTypeCode.INTERNAL);
    }

    public static GetAccountRequest LdbSelfRequest(String id) {
        if(StringUtils.isBlank(id)) throw new IllegalArgumentException("Id value is required");
        return new GetAccountRequest(id, BCeIDAccountTypeCode.LDB);
    }

    public static GetAccountRequest VerifiedIndividualSelfRequest(String id) {
        if(StringUtils.isBlank(id)) throw new IllegalArgumentException("Id value is required");
        return new GetAccountRequest(id, BCeIDAccountTypeCode.VERIFIED_INDIVIDUAL);
    }

}
