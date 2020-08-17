package ca.bc.gov.open.bceid.starter.account.mappers;

import ca.bc.gov.open.bceid.starter.account.models.IndividualIdentity;
import ca.bceid.webservices.client.v9.AccountDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {NameMapper.class, BCeIdMapper.class, AddressMapper.class})
public interface IndividualIdentityMapper {

    @Mapping(target = "dateOfBirth", source="account.individualIdentity.dateOfBirth", qualifiedByName= {"BCeIdMapper", "toDateTime"})
    @Mapping(target = "name", source="account.individualIdentity.name")
    @Mapping(target = "residentialAddress", source="account.individualIdentity.residentialAddress")
    @Mapping(target = "mailingAddress", source="account.individualIdentity.mailingAddress")
    IndividualIdentity toIndividualIdentity(AccountDetailResponse accountDetailResponse);


}
