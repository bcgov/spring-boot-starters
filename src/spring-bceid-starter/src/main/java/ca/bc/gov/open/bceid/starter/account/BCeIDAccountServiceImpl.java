package ca.bc.gov.open.bceid.starter.account;

import ca.bc.gov.open.bceid.starter.BCeIdProperties;
import ca.bc.gov.open.bceid.starter.account.mappers.AccountDetailRequestMapper;
import ca.bc.gov.open.bceid.starter.account.mappers.IndividualIdentityMapper;
import ca.bc.gov.open.bceid.starter.account.models.IndividualIdentity;
import ca.bceid.webservices.client.v9.AccountDetailRequest;
import ca.bceid.webservices.client.v9.AccountDetailResponse;
import ca.bceid.webservices.client.v9.BCeIDServiceSoap;
import ca.bceid.webservices.client.v9.ResponseCode;

import java.util.Optional;

public class BCeIDAccountServiceImpl implements BCeIDAccountService {

    private final BCeIDServiceSoap bCeIDServiceSoap;

    private final BCeIdProperties bCeIdProperties;

    private final AccountDetailRequestMapper accountDetailRequestMapper;

    private final IndividualIdentityMapper individualIdentityMapper;

    public BCeIDAccountServiceImpl(BCeIDServiceSoap bCeIDServiceSoap, BCeIdProperties bCeIdProperties, AccountDetailRequestMapper accountDetailRequestMapper, IndividualIdentityMapper individualIdentityMapper) {
        this.bCeIDServiceSoap = bCeIDServiceSoap;
        this.bCeIdProperties = bCeIdProperties;
        this.accountDetailRequestMapper = accountDetailRequestMapper;
        this.individualIdentityMapper = individualIdentityMapper;
    }

    @Override
    public Optional<IndividualIdentity> getIndividualIdentity(GetAccountRequest request) {

        AccountDetailRequest accountDetailRequest = accountDetailRequestMapper.toAccountDetailRequest(request, bCeIdProperties.getOnlineServiceId());

        AccountDetailResponse response = bCeIDServiceSoap.getAccountDetail(accountDetailRequest);

        if (response.getCode() == ResponseCode.SUCCESS) {

            return Optional.of(individualIdentityMapper.toIndividualIdentity(response));

        }

        return Optional.empty();

    }

}
