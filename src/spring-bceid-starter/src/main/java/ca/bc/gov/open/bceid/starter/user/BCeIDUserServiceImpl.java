package ca.bc.gov.open.bceid.starter.user;

import ca.bceid.webservices.client.v9.AccountDetailRequest;
import ca.bceid.webservices.client.v9.BCeIDServiceSoap;

public class BCeIDUserServiceImpl implements BCeIDUserService {

    private final BCeIDServiceSoap bCeIDServiceSoap;

    public BCeIDUserServiceImpl(BCeIDServiceSoap bCeIDServiceSoap) {
        this.bCeIDServiceSoap = bCeIDServiceSoap;
    }

    @Override
    public String getById(String userId) {

        AccountDetailRequest accountDetailRequest = new AccountDetailRequest();
        return bCeIDServiceSoap.getAccountDetail(accountDetailRequest).getMessage();
    }

}
