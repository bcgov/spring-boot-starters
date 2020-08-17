package ca.bc.gov.open.bceid.starter.account.mappers;

import ca.bceid.webservices.client.v9.BCeIDDateTime;
import ca.bceid.webservices.client.v9.BCeIDString;
import org.joda.time.DateTime;
import org.mapstruct.Named;

import javax.xml.datatype.DatatypeConfigurationException;

@Named("BCeIdMapper")
public interface BCeIdMapper {

    @Named("toString")
    static String toString(BCeIDString bCeIDString) throws DatatypeConfigurationException {
        return bCeIDString.getValue();
    }

    @Named("toDateTime")
    static DateTime toDateTime(BCeIDDateTime bCeIDDateTime) throws DatatypeConfigurationException {
        return new DateTime(bCeIDDateTime.getValue().toGregorianCalendar().getTime());
    }


}
