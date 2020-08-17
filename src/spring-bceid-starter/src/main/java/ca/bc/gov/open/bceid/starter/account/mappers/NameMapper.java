package ca.bc.gov.open.bceid.starter.account.mappers;

import ca.bc.gov.open.bceid.starter.account.models.Name;
import ca.bceid.webservices.client.v9.BCeIDName;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = BCeIdMapper.class)
public interface NameMapper {

    @Mapping(target = "firstName", source = "firstname", qualifiedByName=  { "BCeIdMapper", "toString"} )
    @Mapping(target = "middleName", source = "middleName", qualifiedByName=  { "BCeIdMapper", "toString"} )
    @Mapping(target = "otherMiddleName", source = "otherMiddleName", qualifiedByName=  { "BCeIdMapper", "toString"} )
    @Mapping(target = "surname", source = "surname", qualifiedByName=  { "BCeIdMapper", "toString"} )
    @Mapping(target = "initials", source = "initials", qualifiedByName=  { "BCeIdMapper", "toString"} )
    Name toName(BCeIDName bCeIDName);

}
