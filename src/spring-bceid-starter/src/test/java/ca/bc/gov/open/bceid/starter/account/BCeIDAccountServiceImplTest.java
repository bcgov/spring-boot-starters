package ca.bc.gov.open.bceid.starter.account;

import ca.bc.gov.open.bceid.starter.BCeIdProperties;
import ca.bc.gov.open.bceid.starter.account.models.Address;
import ca.bc.gov.open.bceid.starter.account.models.IndividualIdentity;
import ca.bc.gov.open.bceid.starter.account.models.Name;
import ca.bceid.webservices.client.v9.*;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Test BCeIDAccountServiceImpl")
public class BCeIDAccountServiceImplTest {
    private static final String SUCCESS = "SUCCESS";
    private static final String FIRSTNAME = "FIRSTNAME";
    private static final String MIDDLENAME = "MIDDLENAME";
    private static final String OTHERMIDDLE = "OTHERMIDDLE";
    private static final String SURNAME = "SURNAME";
    private static final String INITIAL = "INITIAL";
    private static final String ADDRESS_1 = "ADDRESS1";
    private static final String ADDRESS_2 = "ADDRESS2";
    private static final String COUNTRY = "COUNTRY";
    private static final String POSTAL = "POSTAL";
    private static final String PROVINCE = "PROVINCE";
    private static final String UNSTRUCTUREDADDRESS = "UNSTRUCTUREDADDRESS";
    private static final String CITY = "CITY";
    private static final String FAILED = "FAILED";
    BCeIDAccountServiceImpl sut;

    @Mock
    BCeIDServiceSoap bCeIDServiceSoapMock;

    @Mock
    BCeIdProperties bCeIdPropertiesMock;


    @BeforeEach
    public void init() {

        sut = new BCeIDAccountServiceImpl(bCeIDServiceSoapMock, bCeIdPropertiesMock);

    }

    @Test
    @DisplayName("Test Return Individual Identity")
    public void withValidRequestReturnAccount() {
        Mockito.when(bCeIDServiceSoapMock.getAccountDetail(any())).thenReturn(createAccountDetailsResponse(ResponseCode.SUCCESS));

        Optional<IndividualIdentity> result = sut.getIndividualIdentity(GetAccountRequest.IndividualSelfRequest(SUCCESS));

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(FIRSTNAME, result.get().getName().getFirstname());
        Assertions.assertEquals(INITIAL, result.get().getName().getInitials());
        Assertions.assertEquals(MIDDLENAME, result.get().getName().getMiddleName());
        Assertions.assertEquals(OTHERMIDDLE, result.get().getName().getOtherMiddleName());
        Assertions.assertEquals(SURNAME, result.get().getName().getSurname());

        Assertions.assertEquals(ADDRESS_1, result.get().getResidentialAddress().getAddressLine1());
        Assertions.assertEquals(ADDRESS_2, result.get().getResidentialAddress().getAddressLine2());
        Assertions.assertEquals(CITY, result.get().getResidentialAddress().getCity());
        Assertions.assertEquals(COUNTRY, result.get().getResidentialAddress().getCountry());
        Assertions.assertEquals(POSTAL, result.get().getResidentialAddress().getPostal());
        Assertions.assertEquals(PROVINCE, result.get().getResidentialAddress().getProvince());
        Assertions.assertEquals(UNSTRUCTUREDADDRESS, result.get().getResidentialAddress().getUnstructuredAddress());

        Assertions.assertEquals(ADDRESS_1, result.get().getMailingAddress().getAddressLine1());
        Assertions.assertEquals(ADDRESS_2, result.get().getMailingAddress().getAddressLine2());
        Assertions.assertEquals(CITY, result.get().getMailingAddress().getCity());
        Assertions.assertEquals(COUNTRY, result.get().getMailingAddress().getCountry());
        Assertions.assertEquals(POSTAL, result.get().getMailingAddress().getPostal());
        Assertions.assertEquals(PROVINCE, result.get().getMailingAddress().getProvince());
        Assertions.assertEquals(UNSTRUCTUREDADDRESS, result.get().getMailingAddress().getUnstructuredAddress());

    }

    @Test
    @DisplayName("Test Return Failure")
    public void withValidRequestReturnFailure() {
        Mockito.when(bCeIDServiceSoapMock.getAccountDetail(any())).thenReturn(createAccountDetailsResponse(ResponseCode.FAILED));

        Optional<IndividualIdentity> result = sut.getIndividualIdentity(GetAccountRequest.IndividualSelfRequest(FAILED));

        Assertions.assertFalse(result.isPresent());
    }

    private BCeIDString getString(String value) {
        BCeIDString stringVal = new BCeIDString();
        stringVal.setValue(value);
        return stringVal;
    }

    private AccountDetailResponse createAccountDetailsResponse(ResponseCode responseCode) {
        AccountDetailResponse accountDetailResponse = new AccountDetailResponse();

        BCeIDAccount account = new BCeIDAccount();

        BCeIDIndividualIdentity identity = new BCeIDIndividualIdentity();

        BCeIDName name = new BCeIDName();

        name.setFirstname(getString(FIRSTNAME));
        name.setMiddleName(getString(MIDDLENAME));
        name.setOtherMiddleName(getString(OTHERMIDDLE));
        name.setSurname(getString(SURNAME));
        name.setInitials(getString(INITIAL));
        identity.setName(name);

        BCeIDAddress address = new BCeIDAddress();
        address.setAddressLine1(getString(ADDRESS_1));
        address.setAddressLine2(getString(ADDRESS_2));
        address.setCity(getString(CITY));
        address.setCountry(getString(COUNTRY));
        address.setPostal(getString(POSTAL));
        address.setProvince(getString(PROVINCE));
        address.setUnstructuredAddress(getString(UNSTRUCTUREDADDRESS));
        identity.setResidentialAddress(address);
        identity.setMailingAddress(address);

        account.setIndividualIdentity(identity);

        accountDetailResponse.setAccount(account);

        accountDetailResponse.setCode(responseCode);
        return accountDetailResponse;
    }
}
