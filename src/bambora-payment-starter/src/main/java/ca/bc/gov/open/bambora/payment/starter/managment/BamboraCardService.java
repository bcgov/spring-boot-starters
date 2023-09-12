package ca.bc.gov.open.bambora.payment.starter.managment;

import java.net.URI;

import ca.bc.gov.open.bambora.payment.starter.managment.models.RecurringPaymentDetails;

public interface BamboraCardService {
	
	URI setupRecurringPayment(RecurringPaymentDetails recurringPaymentDetails);

}
