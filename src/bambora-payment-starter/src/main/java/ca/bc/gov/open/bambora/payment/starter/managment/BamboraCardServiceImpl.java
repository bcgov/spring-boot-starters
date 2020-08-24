package ca.bc.gov.open.bambora.payment.starter.managment;

import ca.bc.gov.open.bambora.payment.starter.BamboraException;
import ca.bc.gov.open.bambora.payment.starter.BamboraProperties;
import ca.bc.gov.open.bambora.payment.starter.managment.models.RecurringPaymentDetails;
import com.sun.jndi.toolkit.url.Uri;

import java.net.MalformedURLException;

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
        String paramString = BeanstreamConstants.PARAM_PPRDIR_SERVICE_VERSION +
                "=" + this.hostedProfileServiceVersion +
                "&" + BeanstreamConstants.PARAM_PPRDIR_MERCHANT_ID +
                "=" + this.merchantId +
                "&" + BeanstreamConstants.PARAM_PPRDIR_LANGUAGE +
                "=" + BeanstreamConstants.LanguageType.eng.toString() +
                "&" + BeanstreamConstants.PARAM_PPRDIR_OPERATION_TYPE +
                "=" + opType.toString() +
                "&" + BeanstreamConstants.PARAM_PPRDIR_REF1 +
                "=" + echoData +
                "&" + BeanstreamConstants.PARAM_PPRDIR_RETURN_URL +
                "=" + redirectURL +
                "&" + BeanstreamConstants.PARAM_PPRDIR_ORDER_NUMBER +
                "=" + orderNumber;

        // if doing an update, supply tyhe customer code.
        if ( (null != opType) && (opType == BeanstreamConstants.OperationTypes.M) ) {
            paramString = paramString + "&" + BeanstreamConstants.PARAM_PPRDIR_CUSTOMER_CODE +
                    "=" + endUserId;
        }

        //replace spaces with "%20" (do not do a full URL encoding. Doesn't work with beanstream.
        paramString =  paramString.replace(" ",  "%20");

        //add hash key at end of params
        paramString = paramString + this.key;

        // Calculate the MD5 value using the Hash Key set on the Order Settings page (Within Beanstream account).
        // How:
        // Place the hash key after the last parameter.
        // Perform an SHA-1 hash on the text up to the end of the key, then
        // Replace the hash key with hashValue=[hash result].
        // Add the result to the hosted service url.
        // Note: Hash is calculated on the params ONLY.. Does NOT include the hosted payment page url.
        // See http://support.beanstream.com/#docs/about-hash-validation.htm?Highlight=hash for more info.
        log.log(Level.INFO, "Calculating MD5 on paramString " + paramString);
        String hashed = getHash(paramString);

        // Calculate the expiry based on the minutesToExpire value.
        SimpleDateFormat sdfDate = new SimpleDateFormat(BeanstreamConstants.PARAM_TRANS_HASH_EXPIRY_FORMAT);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE,  this.minutesToExpire);
        String expiry = sdfDate.format(cal.getTime());

        // Add hash and expiry to the redirect
        paramString = paramString.replace(this.key,
                "&" + BeanstreamConstants.PARAM_TRANS_HASH_VALUE + "=" + hashed +
                        "&" + BeanstreamConstants.PARAM_TRANS_HASH_EXPIRY + "=" + expiry);

        String redirect = this.hostedProfileURL + "?" + paramString;
        return new Uri("blarg");
    }
}
