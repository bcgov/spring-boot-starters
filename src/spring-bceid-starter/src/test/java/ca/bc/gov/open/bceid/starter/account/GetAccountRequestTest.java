package ca.bc.gov.open.bceid.starter.account;

import ca.bceid.webservices.client.v9.BCeIDAccountTypeCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Test BCeIDAccountServiceImpl")
public class GetAccountRequestTest {

    private static final String ID = "ID";

    @Test
    @DisplayName("Test Static BusinessSelfRequest")
    public void testStaticMethodBusinessSelfRequest() {
        GetAccountRequest result = GetAccountRequest.BusinessSelfRequest(ID);

        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(ID, result.getRequesterId());
        Assertions.assertEquals(BCeIDAccountTypeCode.BUSINESS, result.getbCeIDAccountTypeCode());
        Assertions.assertEquals(BCeIDAccountTypeCode.BUSINESS, result.getRequesterBCeIDAccountTypeCode());
    }

    @Test
    @DisplayName("Test Static EdsSelfRequest")
    public void testStaticMethodEdsSelfRequest() {
        GetAccountRequest result = GetAccountRequest.EdsSelfRequest(ID);

        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(ID, result.getRequesterId());
        Assertions.assertEquals(BCeIDAccountTypeCode.EDS, result.getbCeIDAccountTypeCode());
        Assertions.assertEquals(BCeIDAccountTypeCode.EDS, result.getRequesterBCeIDAccountTypeCode());
    }


    @Test
    @DisplayName("Test Static IndividualSelfRequest")
    public void testStaticMethodIndividualSelfRequest() {
        GetAccountRequest result = GetAccountRequest.IndividualSelfRequest(ID);

        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(ID, result.getRequesterId());
        Assertions.assertEquals(BCeIDAccountTypeCode.INDIVIDUAL, result.getbCeIDAccountTypeCode());
        Assertions.assertEquals(BCeIDAccountTypeCode.INDIVIDUAL, result.getRequesterBCeIDAccountTypeCode());
    }


    @Test
    @DisplayName("Test Static InternalSelfRequest")
    public void testStaticMethodInternalSelfRequest() {
        GetAccountRequest result = GetAccountRequest.InternalSelfRequest(ID);

        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(ID, result.getRequesterId());
        Assertions.assertEquals(BCeIDAccountTypeCode.INTERNAL, result.getbCeIDAccountTypeCode());
        Assertions.assertEquals(BCeIDAccountTypeCode.INTERNAL, result.getRequesterBCeIDAccountTypeCode());
    }

    @Test
    @DisplayName("Test Static LdbSelfRequest")
    public void testStaticMethodLdbSelfRequest() {
        GetAccountRequest result = GetAccountRequest.LdbSelfRequest(ID);

        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(ID, result.getRequesterId());
        Assertions.assertEquals(BCeIDAccountTypeCode.LDB, result.getbCeIDAccountTypeCode());
        Assertions.assertEquals(BCeIDAccountTypeCode.LDB, result.getRequesterBCeIDAccountTypeCode());
    }

    @Test
    @DisplayName("Test Static VerifiedIndividualSelfRequest")
    public void testStaticMethodVerifiedIndividualSelfRequest() {
        GetAccountRequest result = GetAccountRequest.VerifiedIndividualSelfRequest(ID);

        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(ID, result.getRequesterId());
        Assertions.assertEquals(BCeIDAccountTypeCode.VERIFIED_INDIVIDUAL, result.getbCeIDAccountTypeCode());
        Assertions.assertEquals(BCeIDAccountTypeCode.VERIFIED_INDIVIDUAL, result.getRequesterBCeIDAccountTypeCode());
    }

    @Test
    @DisplayName("Test No Id Exception")
    public void testException() {
        Assertions.assertThrows(IllegalArgumentException.class,() -> GetAccountRequest.IndividualSelfRequest(null));
    }
}
