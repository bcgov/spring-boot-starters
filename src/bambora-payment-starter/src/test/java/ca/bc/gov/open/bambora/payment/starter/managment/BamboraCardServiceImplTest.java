package ca.bc.gov.open.bambora.payment.starter.managment;

import ca.bc.gov.open.bambora.payment.starter.BamboraException;
import ca.bc.gov.open.bambora.payment.starter.BamboraProperties;
import ca.bc.gov.open.bambora.payment.starter.managment.models.RecurringPaymentDetails;
import com.sun.jndi.toolkit.url.Uri;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Test BamboraCardServiceImpl")
public class BamboraCardServiceImplTest {
    private static final String PROFILE_SERVICE_VERSION = "HOSTEDPROFILE";
    private static final String HOSTED_PROFILE_URL = "http://localhost";
    private static final String KEY = "XTN123TNV123";
    private static final String MERCHANT_ID = "123";
    private static final int MINUTES_TO_EXPIRY = 12;
    private static final String ORDERNUM = "ORDERNUM";
    private static final String ECHODATA = "ECHODATA";
    private static final String REDIRECTURL = "REDIRECTURL";
    private static final String END_USER_ID = "123";
    private static final String BAMBORA_CLIENT_URL = "http://localhost?serviceVersion=HOSTEDPROFILE&merchantId=123&trnLanguage=eng&operationType=M&ref1=ECHODATA&trnReturnURL=REDIRECTURL&trnOrderNumber=ORDERNUM&customerCode=123";
    private static final String BAMBORA_NEW_URL = "http://localhost?serviceVersion=HOSTEDPROFILE&merchantId=123&trnLanguage=eng&operationType=N&ref1=ECHODATA&trnReturnURL=REDIRECTURL&trnOrderNumber=ORDERNUM";

    private BamboraCardServiceImpl sut;

    private BamboraProperties bamboraProperties;

    @BeforeEach
    public void init() {

        bamboraProperties = new BamboraProperties();
        bamboraProperties.setHostedProfileServiceVersion(PROFILE_SERVICE_VERSION);
        bamboraProperties.setHostedProfileUrl(HOSTED_PROFILE_URL);
        bamboraProperties.setHashKey(KEY);
        bamboraProperties.setMerchantId(MERCHANT_ID);
        bamboraProperties.setMinutesToExpiry(MINUTES_TO_EXPIRY);

    }

    @Test
    @DisplayName("With client create update url")
    public void withClientIdCreateUpdateUrl() {
        sut = new BamboraCardServiceImpl(bamboraProperties);

        Uri actual = sut.setupRecurringPayment(createPaymentDetail(END_USER_ID));

        Assertions.assertNotNull(actual);
        Assertions.assertTrue(actual.toString().contains(BAMBORA_CLIENT_URL));
    }


    @Test
    @DisplayName("Without client create update url")
    public void withoutClientIdCreateUpdateUrl() {
        sut = new BamboraCardServiceImpl(bamboraProperties);

        Uri actual = sut.setupRecurringPayment(createPaymentDetail(null));

        Assertions.assertNotNull(actual);
        Assertions.assertTrue(actual.toString().contains(BAMBORA_NEW_URL));
    }

    @Test
    @DisplayName("With invalid paramter throw invalid url exception")
    public void withInvalidParamterThrowException() {
        bamboraProperties.setHostedProfileUrl("NOTAURL");
        sut = new BamboraCardServiceImpl(bamboraProperties);
        Assertions.assertThrows(BamboraException.class, () -> sut.setupRecurringPayment(createPaymentDetail(END_USER_ID)));
    }

    private RecurringPaymentDetails createPaymentDetail(String endUserId) {
        return RecurringPaymentDetails.builder()
                .orderNumber(ORDERNUM)
                .echoData(ECHODATA)
                .redirectURL(REDIRECTURL)
                .endUserId(endUserId)
                .create();
    }
}
