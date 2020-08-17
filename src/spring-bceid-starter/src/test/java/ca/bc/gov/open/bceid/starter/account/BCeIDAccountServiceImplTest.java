package ca.bc.gov.open.bceid.starter.account;

import ca.bc.gov.open.bceid.starter.BCeIdProperties;
import ca.bc.gov.open.bceid.starter.account.mappers.AccountDetailRequestMapper;
import ca.bc.gov.open.bceid.starter.account.mappers.AccountDetailRequestMapperImpl;
import ca.bc.gov.open.bceid.starter.account.mappers.IndividualIdentityMapper;
import ca.bc.gov.open.bceid.starter.account.mappers.IndividualIdentityMapperImpl;
import ca.bc.gov.open.bceid.starter.account.models.IndividualIdentity;
import ca.bceid.webservices.client.v9.*;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

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
    private BCeIDServiceSoap bCeIDServiceSoapMock;

    @Mock
    private BCeIdProperties bCeIdPropertiesMock;

    private AccountDetailRequestMapper accountDetailRequestMapper;

    private IndividualIdentityMapper individualIdentityMapper;

    @BeforeEach
    public void init() {

        MockitoAnnotations.initMocks(this);

        Mockito.doReturn(createAccountDetailsResponse(ResponseCode.SUCCESS))
                .when(bCeIDServiceSoapMock)
                .getAccountDetail(ArgumentMatchers.argThat(x -> x.getUserGuid().equals(SUCCESS) && x.getAccountTypeCode() == BCeIDAccountTypeCode.INDIVIDUAL));

        Mockito
                .doReturn(createAccountDetailsResponse(ResponseCode.FAILED))
                .when(bCeIDServiceSoapMock)
                .getAccountDetail(ArgumentMatchers.argThat(x -> x.getUserGuid().equals(FAILED)));

        // Testing mapper as part of the test
        accountDetailRequestMapper = new AccountDetailRequestMapperImpl();
        individualIdentityMapper = new IndividualIdentityMapperImpl();

        sut = new BCeIDAccountServiceImpl(bCeIDServiceSoapMock, bCeIdPropertiesMock, accountDetailRequestMapper, individualIdentityMapper);

    }

    @Test
    @DisplayName("Test Return Individual Identity")
    public void withValidRequestReturnAccount() {

        Optional<IndividualIdentity> result = sut.getIndividualIdentity(GetAccountRequest.IndividualSelfRequest(SUCCESS));

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(FIRSTNAME, result.get().getName().getFirstName());
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

        Assertions.assertNotNull(result.get().getDateOfBirth());

    }

    @Test
    @DisplayName("Test Return Failure")
    public void withValidRequestReturnFailure() {

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
        BCeIDDateTime birthDate = new BCeIDDateTime();
        birthDate.setValue(getDate());
        identity.setDateOfBirth(birthDate);

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
    private XMLGregorianCalendar getDate()  {

        Calendar createDate = Calendar.getInstance();
        Date cDate = createDate.getTime();
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(cDate);
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        } catch (DatatypeConfigurationException e) {
            return null;
        }

    }

}
