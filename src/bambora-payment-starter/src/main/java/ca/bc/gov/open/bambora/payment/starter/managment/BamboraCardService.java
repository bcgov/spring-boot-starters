package ca.bc.gov.open.bambora.payment.starter.managment;

import ca.bc.gov.open.bambora.payment.starter.managment.models.RecurringPaymentDetails;
import com.sun.jndi.toolkit.url.Uri;

public interface BamboraCardService {
    Uri setupRecurringPayment(RecurringPaymentDetails recurringPaymentDetails);

}
