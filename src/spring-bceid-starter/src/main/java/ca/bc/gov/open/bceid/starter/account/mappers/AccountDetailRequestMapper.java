package ca.bc.gov.open.bceid.starter.account.mappers;

import ca.bc.gov.open.bceid.starter.account.GetAccountRequest;
import ca.bceid.webservices.client.v9.AccountDetailRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AccountDetailRequestMapper {

    @Mapping(target = "userGuid", source = "getAccountRequest.id")
    @Mapping(target = "accountTypeCode", source="getAccountRequest.bCeIDAccountTypeCode")
    @Mapping(target = "requesterUserGuid", source="getAccountRequest.requesterId")
    @Mapping(target = "requesterAccountTypeCode", source="getAccountRequest.requesterBCeIDAccountTypeCode")
    @Mapping(target = "onlineServiceId", source = "onlineServiceId")
    AccountDetailRequest toAccountDetailRequest(GetAccountRequest getAccountRequest, String onlineServiceId);

}
