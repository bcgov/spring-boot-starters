package ca.bc.gov.open.bceid.starter.account;

import ca.bc.gov.open.bceid.starter.BCeIdProperties;
import ca.bc.gov.open.bceid.starter.account.models.Address;
import ca.bc.gov.open.bceid.starter.account.models.IndividualIdentity;
import ca.bc.gov.open.bceid.starter.account.models.Name;
import ca.bceid.webservices.client.v9.*;
import org.joda.time.DateTime;

import java.util.Optional;

public class BCeIDAccountServiceImpl implements BCeIDAccountService {

    private final BCeIDServiceSoap bCeIDServiceSoap;

    private final BCeIdProperties bCeIdProperties;

    public BCeIDAccountServiceImpl(BCeIDServiceSoap bCeIDServiceSoap, BCeIdProperties bCeIdProperties) {
        this.bCeIDServiceSoap = bCeIDServiceSoap;
        this.bCeIdProperties = bCeIdProperties;
    }

    @Override
    public Optional<IndividualIdentity> getIndividualIdentity(GetAccountRequest request) {

        AccountDetailRequest accountDetailRequest = new AccountDetailRequest();
        accountDetailRequest.setOnlineServiceId(bCeIdProperties.getOnlineServiceId());
        accountDetailRequest.setRequesterUserGuid(request.getId());
        accountDetailRequest.setRequesterAccountTypeCode(request.getbCeIDAccountTypeCode());
        accountDetailRequest.setUserGuid(request.getRequesterId());
        accountDetailRequest.setAccountTypeCode(request.getRequesterBCeIDAccountTypeCode());
        AccountDetailResponse response = bCeIDServiceSoap.getAccountDetail(accountDetailRequest);

        if (response.getCode() == ResponseCode.SUCCESS) {

            Name name = new Name(
                    response.getAccount().getIndividualIdentity().getName().getFirstname().getValue(),
                    response.getAccount().getIndividualIdentity().getName().getMiddleName().getValue(),
                    response.getAccount().getIndividualIdentity().getName().getOtherMiddleName().getValue(),
                    response.getAccount().getIndividualIdentity().getName().getSurname().getValue(),
                    response.getAccount().getIndividualIdentity().getName().getInitials().getValue());


            BCeIDAddress bresidentialAddress = response.getAccount().getIndividualIdentity().getResidentialAddress();

            Address residentialAddress = new Address(bresidentialAddress.getAddressLine1().getValue(),
                    bresidentialAddress.getAddressLine2().getValue(),
                    bresidentialAddress.getCity().getValue(),
                    bresidentialAddress.getPostal().getValue(),
                    bresidentialAddress.getProvince().getValue(),
                    bresidentialAddress.getCountry().getValue(),
                    bresidentialAddress.getUnstructuredAddress().getValue());

            BCeIDAddress bmailingAddress = response.getAccount().getIndividualIdentity().getMailingAddress();

            Address mailingAddress = new Address(bmailingAddress.getAddressLine1().getValue(),
                    bmailingAddress.getAddressLine2().getValue(),
                    bmailingAddress.getCity().getValue(),
                    bmailingAddress.getPostal().getValue(),
                    bmailingAddress.getProvince().getValue(),
                    bmailingAddress.getCountry().getValue(),
                    bmailingAddress.getUnstructuredAddress().getValue());

            return Optional.of(new IndividualIdentity(
                    name,
                    new DateTime(response.getAccount().getIndividualIdentity().getDateOfBirth().getValue().toGregorianCalendar().getTime()),
                    residentialAddress,
                    mailingAddress));

        }

        return Optional.empty();

    }

}
