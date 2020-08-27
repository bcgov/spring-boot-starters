package ca.bc.gov.open.bambora.payment.starter.managment;

import ca.bc.gov.open.bambora.payment.starter.BamboraConstants;
import ca.bc.gov.open.bambora.payment.starter.BamboraException;
import ca.bc.gov.open.bambora.payment.starter.BamboraProperties;
import ca.bc.gov.open.bambora.payment.starter.managment.models.RecurringPaymentDetails;
import com.sun.jndi.toolkit.url.Uri;
import org.apache.commons.codec.digest.DigestUtils;

import java.net.MalformedURLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BamboraCardServiceImpl implements BamboraCardService {

    private final BamboraProperties bamboraProperties;

    public BamboraCardServiceImpl(BamboraProperties bamboraProperties) {
        this.bamboraProperties = bamboraProperties;
    }

    @Override
    public Uri setupRecurringPayment(RecurringPaymentDetails recurringPaymentDetails) {
        try {
            return buildRecurringPaymentUrl(recurringPaymentDetails);
        } catch (MalformedURLException e) {
            throw new BamboraException("Url construction failed", e.getCause());
        }
    }


    private Uri buildRecurringPaymentUrl(RecurringPaymentDetails recurringPaymentDetails) throws MalformedURLException {

        String operationType = (recurringPaymentDetails.getEndUserId() != null ? BamboraConstants.OperationTypes.M.toString() : BamboraConstants.OperationTypes.N.toString());

        StringBuilder paramString = new StringBuilder();

        paramString.append(formatBamboraParam("", BamboraConstants.PARAM_PPRDIR_SERVICE_VERSION, bamboraProperties.getHostedProfileServiceVersion()));

        paramString.append(formatBamboraParam("&", BamboraConstants.PARAM_PPRDIR_MERCHANT_ID, bamboraProperties.getMerchantId()));

        paramString.append(formatBamboraParam("&", BamboraConstants.PARAM_PPRDIR_LANGUAGE, BamboraConstants.LANGUAGE_TYPE));

        paramString.append(formatBamboraParam("&", BamboraConstants.PARAM_PPRDIR_OPERATION_TYPE, operationType));

        paramString.append(formatBamboraParam("&", BamboraConstants.PARAM_PPRDIR_REF1, recurringPaymentDetails.getEchoData()));

        paramString.append(formatBamboraParam("&", BamboraConstants.PARAM_PPRDIR_RETURN_URL, recurringPaymentDetails.getRedirectURL()));

        paramString.append(formatBamboraParam("&", BamboraConstants.PARAM_PPRDIR_ORDER_NUMBER, recurringPaymentDetails.getOrderNumber()));

        if (operationType.equals(BamboraConstants.OperationTypes.M.toString()))
            paramString.append(formatBamboraParam("&", BamboraConstants.PARAM_PPRDIR_CUSTOMER_CODE, recurringPaymentDetails.getEndUserId()));

        paramString.append(MessageFormat.format("&{0}={1}&{2}={3}",  BamboraConstants.PARAM_TRANS_HASH_VALUE, getHash(paramString.toString()), BamboraConstants.PARAM_TRANS_HASH_EXPIRY, getExpiry()));

        return new Uri(MessageFormat.format("{0}?{1}", bamboraProperties.getHostedProfileUrl(), paramString.toString()));

    }

    private String getExpiry() {
        SimpleDateFormat sdfDate = new SimpleDateFormat(BamboraConstants.PARAM_TRANS_HASH_EXPIRY_FORMAT);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE,  bamboraProperties.getMinutesToExpiry());
        return sdfDate.format(cal.getTime());
    }

    private String formatBamboraParam(String prefix, String key, String value) {
        return MessageFormat.format("{0}{1}={2}", prefix, key, value).replace(" ",  "%20");
    }

    private String getHash(String message) {
        String digest = DigestUtils.md5Hex(MessageFormat.format("{0}{1}", message, bamboraProperties.getHashKey()));
        return digest.toUpperCase();
    }
}
