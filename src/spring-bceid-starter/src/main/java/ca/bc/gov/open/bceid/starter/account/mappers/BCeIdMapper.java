package ca.bc.gov.open.bceid.starter.account.mappers;

import ca.bceid.webservices.client.v9.BCeIDDateTime;
import ca.bceid.webservices.client.v9.BCeIDString;
import org.joda.time.DateTime;
import org.mapstruct.Named;

@Named("BCeIdMapper")
public interface BCeIdMapper {

    @Named("toString")
    static String toString(BCeIDString bCeIDString) {
        return bCeIDString.getValue();
    }

    @Named("toDateTime")
    static DateTime toDateTime(BCeIDDateTime bCeIDDateTime) {
        return new DateTime(bCeIDDateTime.getValue().toGregorianCalendar().getTime());
    }


}
