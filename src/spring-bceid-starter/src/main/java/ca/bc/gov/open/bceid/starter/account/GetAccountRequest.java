package ca.bc.gov.open.bceid.starter.account;

import ca.bceid.webservices.client.v9.BCeIDAccountTypeCode;
import org.apache.commons.lang3.StringUtils;

public class GetAccountRequest {

    private String id;
    private BCeIDAccountTypeCode bCeIDAccountTypeCode;
    private String requesterId;
    private BCeIDAccountTypeCode requesterBCeIDAccountTypeCode;

    private GetAccountRequest(String id, BCeIDAccountTypeCode bCeIDAccountTypeCode) {
        if(StringUtils.isBlank(id)) throw new IllegalArgumentException("Id value is required");
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
        return new GetAccountRequest(id, BCeIDAccountTypeCode.BUSINESS);
    }

    /**
     * Build a request EDS Federated Accounts
     * @param id
     * @return
     */
    public static GetAccountRequest EdsSelfRequest(String id) {
        return new GetAccountRequest(id, BCeIDAccountTypeCode.EDS);
    }

    /**
     * Build a request for individual
     * @param id
     * @return
     */
    public static GetAccountRequest IndividualSelfRequest(String id) {
        return new GetAccountRequest(id, BCeIDAccountTypeCode.INDIVIDUAL);
    }

    /**
     * Build a request for internal
     * @param id
     * @return
     */
    public static GetAccountRequest InternalSelfRequest(String id) {
        return new GetAccountRequest(id, BCeIDAccountTypeCode.INTERNAL);
    }

    /**
     * Build a request for Liquor Distribution Branch Federated Accounts
     * @param id
     * @return
     */
    public static GetAccountRequest LdbSelfRequest(String id) {
        return new GetAccountRequest(id, BCeIDAccountTypeCode.LDB);
    }

    /**
     * Build a request for Verified Individual.
     * @param id
     * @return
     */
    public static GetAccountRequest VerifiedIndividualSelfRequest(String id) {
        return new GetAccountRequest(id, BCeIDAccountTypeCode.VERIFIED_INDIVIDUAL);
    }

}
