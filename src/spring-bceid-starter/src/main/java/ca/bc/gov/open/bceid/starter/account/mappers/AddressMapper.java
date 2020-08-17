package ca.bc.gov.open.bceid.starter.account.mappers;

import ca.bc.gov.open.bceid.starter.account.models.Address;
import ca.bceid.webservices.client.v9.BCeIDAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = { BCeIdMapper.class })
public interface AddressMapper {

    @Mapping(source="addressLine1", target = "addressLine1", qualifiedByName= {"BCeIdMapper", "toString"})
    @Mapping(source="addressLine2", target = "addressLine2", qualifiedByName= {"BCeIdMapper", "toString"})
    @Mapping(source="city", target = "city", qualifiedByName= {"BCeIdMapper", "toString"})
    @Mapping(source="postal", target = "postal", qualifiedByName= {"BCeIdMapper", "toString"})
    @Mapping(source="province", target = "province", qualifiedByName= {"BCeIdMapper", "toString"})
    @Mapping(source="country", target = "country", qualifiedByName= {"BCeIdMapper", "toString"})
    @Mapping(source="unstructuredAddress", target = "unstructuredAddress", qualifiedByName= {"BCeIdMapper", "toString"})
    Address toAddress(BCeIDAddress bCeIDAddress);

}
